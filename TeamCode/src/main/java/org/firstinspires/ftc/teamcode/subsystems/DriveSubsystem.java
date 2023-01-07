package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.RawPower;
import static com.arcrobotics.ftclib.hardware.motors.Motor.ZeroPowerBehavior.BRAKE;

import static org.firstinspires.ftc.teamcode.roadrunner.util.Encoder.Direction.REVERSE;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.firstinspires.ftc.robotcore.external.Telemetry;
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

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
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

        telemetry.addData("Drive (Heading)", "%.2f°", getHeading());
        telemetry.addData("Drive (Pose)", drivePose.toString());
        telemetry.addData("Drive (LF)", "%.2f pow, %d pos, %.2f dist", hardware.driveLeftFront.get(), hardware.driveLeftFront.getCurrentPosition(), hardware.driveLeftFront.getDistance());
        telemetry.addData("Drive (RF)", "%.2f pow, %d pos, %.2f dist", hardware.driveRightFront.get(), hardware.driveRightFront.getCurrentPosition(), hardware.driveRightFront.getDistance());
        telemetry.addData("Drive (LR)", "%.2f pow, %d pos, %.2f dist", hardware.driveLeftRear.get(), hardware.driveLeftRear.getCurrentPosition(), hardware.driveLeftRear.getDistance());
        telemetry.addData("Drive (RR)", "%.2f pow, %d pos, %.2f dist", hardware.driveRightRear.get(), hardware.driveRightRear.getCurrentPosition(), hardware.driveRightRear.getDistance());
    }

    public void inputs(double strafe, double forward, double turn) {
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
        return Math.toDegrees(hardware.imu.getAngularOrientation().firstAngle);
    }

    public Pose2d getPose() {
        return odometry.getPoseEstimate();
    }

    public void setPose(Pose2d pose) {
        odometry.setPoseEstimate(pose);
    }

    public void to(Pose2d[] poses) {
        followTrajectoryAsync(
            builder -> Arrays.stream(poses).forEach(builder::lineToLinearHeading)
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
