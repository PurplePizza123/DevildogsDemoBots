package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.RawPower;
import static com.arcrobotics.ftclib.hardware.motors.Motor.ZeroPowerBehavior.BRAKE;

import static org.firstinspires.ftc.teamcode.roadrunner.util.Encoder.Direction.REVERSE;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.Odometry;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.Arrays;

@Config
public class DriveSubsystem extends HardwareSubsystem {
    public static double PULSE_PER_ROTATION = 537.7;
    public static double DISTANCE_PER_ROTATION = 3.78 * Math.PI;
    public static double DISTANCE_PER_PULSE = DISTANCE_PER_ROTATION / PULSE_PER_ROTATION;
    public static Motor.RunMode RUN_MODE = RawPower;
    public static boolean DRIVE_FIELD_CENTRIC = false;
    public static boolean SQUARE_INPUTS = false;
    public static boolean AUTO_INVERT = false;

    private final MecanumDrive drive;
    private final Odometry odometry;
    private static Pose2d drivePose = new Pose2d();
    public static double power = 0.5;

    public DriveSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        hardware.imu.initialize(parameters);


        hardware.driveLeftFront.setInverted(true);
        hardware.driveLeftRear.setInverted(true);

        hardware.drive.setRunMode(RUN_MODE);
        hardware.drive.setZeroPowerBehavior(BRAKE);
        hardware.drive.setDistancePerPulse(DISTANCE_PER_PULSE);
        hardware.drive.resetEncoder();

        drive = new MecanumDrive(
            AUTO_INVERT,
            hardware.driveLeftFront,
            hardware.driveRightFront,
            hardware.driveLeftRear,
            hardware.driveRightRear
        );

        hardware.odometryLeft.setDirection(REVERSE);
        hardware.odometryRight.setDirection(REVERSE);
        hardware.odometryCenter.setDirection(REVERSE);

        odometry = new Odometry(hardware, telemetry);

        odometry.setPoseEstimate(drivePose);
    }

    @Override
    public void periodic() {
        odometry.update();

        drivePose = getPose();

        telemetry.addData("IMU (Heading)", "%.2f°", getHeading());
        telemetry.addData("IMU (Pitch)", "%.2f°", getPitch());
        telemetry.addData("IMU (Roll)", "%.2f°", getRoll());
        telemetry.addData("Drive (Pose)", drivePose.toString());
        telemetry.addData("Drive (LF)", "%.2f pow, %d pos, %.2f dist", hardware.driveLeftFront.get(), hardware.driveLeftFront.getCurrentPosition(), hardware.driveLeftFront.getDistance());
        telemetry.addData("Drive (RF)", "%.2f pow, %d pos, %.2f dist", hardware.driveRightFront.get(), hardware.driveRightFront.getCurrentPosition(), hardware.driveRightFront.getDistance());
        telemetry.addData("Drive (LR)", "%.2f pow, %d pos, %.2f dist", hardware.driveLeftRear.get(), hardware.driveLeftRear.getCurrentPosition(), hardware.driveLeftRear.getDistance());
        telemetry.addData("Drive (RR)", "%.2f pow, %d pos, %.2f dist", hardware.driveRightRear.get(), hardware.driveRightRear.getCurrentPosition(), hardware.driveRightRear.getDistance());
    }

    public void inputs(double strafe, double forward, double turn) {
        if (strafe + forward + turn != 0) odometry.followTrajectorySequenceAsync(null);
        else if (odometry.isBusy()) return;
        strafe *= power; forward *= power; turn *= power;
        if (DRIVE_FIELD_CENTRIC) drive.driveFieldCentric(strafe, forward, turn, getHeading(), SQUARE_INPUTS);
        else drive.driveRobotCentric(strafe, forward, turn, SQUARE_INPUTS);
    }

    public void strafe(double distance) {
        followTrajectoryAsync(
            builder -> {
                if (distance < 0) builder.strafeLeft(-distance);
                else builder.strafeRight(distance);
            }
        );
    }

    public void forward(double distance) {
        followTrajectoryAsync(
            builder -> {
                if (distance < 0) builder.back(-distance);
                else builder.forward(distance);
            }
        );
    }

    public void turn(double heading) {
        odometry.turnAsync(
            Math.toRadians(heading)
        );
    }

    public double getHeading() {
        return Math.toDegrees(
            hardware.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)
        );
    }

    public double getPitch() {
        return Math.toDegrees(
            hardware.imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.RADIANS)
        );
    }

    public double getRoll() {
        return Math.toDegrees(
            hardware.imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.RADIANS)
        );
    }


    public Pose2d getPose() {
        odometry.update();
        return odometry.getPoseEstimate();
    }

    public void setPose(Pose2d pose) {
        odometry.setPoseEstimate(pose);
    }

    public void to(Pose2d[] poses) {
        followTrajectoryAsync(
            builder -> {
                for (int i = 1; i < poses.length; i++) {
                    Pose2d prev = poses[i - 1];
                    Pose2d curr = poses[i];

                    double remainder = curr.getHeading() - prev.getHeading();
                    if (remainder > +Math.PI) remainder -= Math.PI * 2;
                    if (remainder < -Math.PI) remainder += Math.PI * 2;

                    if (remainder > +Math.PI * 0.9 || remainder < -Math.PI * 0.9) {
                        builder.lineToConstantHeading(new Vector2d(curr.getX(), curr.getY()));
                    } else {
                        builder.lineToLinearHeading(
                            new Pose2d(
                                prev.getX() * 1.0001,
                                prev.getY() * 1.0001,
                                curr.getHeading()
                            )
                        );

                        builder.lineToLinearHeading(curr);
                    }
                }
            }
        );
    }

    public boolean isBusy() {
        return odometry.isBusy();
    }

    private void followTrajectoryAsync(Consumer<TrajectorySequenceBuilder> consumer) {
        Pose2d current = getPose();
        TrajectorySequenceBuilder builder = odometry.trajectorySequenceBuilder(current);
        consumer.accept(builder);
        odometry.followTrajectorySequenceAsync(builder.build());
    }
}
