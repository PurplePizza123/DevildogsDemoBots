package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.RawPower;
import static com.arcrobotics.ftclib.hardware.motors.Motor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLACK;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.GREEN;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;
import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.mmPerInch;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.roadrunner.util.Encoder.Direction.REVERSE;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.Odometry;
import org.firstinspires.ftc.teamcode.hacks.VuforiaFieldNavigation;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

@Config
public class DriveSubsystem extends HardwareSubsystem {
    public static double PULSE_PER_ROTATION = 537.7;
    public static double DISTANCE_PER_ROTATION = 3.78 * Math.PI;
    public static double DISTANCE_PER_PULSE = DISTANCE_PER_ROTATION / PULSE_PER_ROTATION;
    public static Motor.RunMode RUN_MODE = RawPower;
    public static boolean DRIVE_FIELD_CENTRIC = false;
    public static boolean SQUARE_INPUTS = false;
    public static boolean AUTO_INVERT = false;
    public static double ALLOWABLE_TILT = 10;
    public static double ALLOWABLE_STILL = 1.0;
    public static double TILE_WIDTH = 23.5;
    public double power = 0.5;

    private final MecanumDrive drive;
    private final Odometry odometry;

    private final VuforiaFieldNavigation vuforia;
    private Pose2d navPoseRaw = new Pose2d();
    private Pose2d navPoseAvg = new Pose2d();
    private double[] navPoseSum = new double[4];

    public DriveSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);

        hardware.imu.initialize(
            new IMU.Parameters(
                new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
            )
        );

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

        odometry.setPoseEstimate(config.pose);

        vuforia = new VuforiaFieldNavigation(hardware.rearWebcam);
    }

    @Override
    public void periodic() {
        hardware.clearBulkCache();

        config.pose = getPose();

        if (isTilted() || (config.auto && config.timer.seconds() > 29.9)) {
            odometry.followTrajectorySequenceAsync(null);
            inputs(0,0,0);
        }

        telemetry.addData("IMU (Roll)", "%.2f°, %.2f°/s", Math.toDegrees(getRoll()), Math.toDegrees(getRollRate()));
        telemetry.addData("IMU (Pitch)", "%.2f°, %.2f°/s", Math.toDegrees(getPitch()), Math.toDegrees(getPitchRate()));
        telemetry.addData("IMU (Yaw)", "%.2f°, %.2f°/s", Math.toDegrees(getYaw()), Math.toDegrees(getYawRate()));
        telemetry.addData("IMU (Tilted)", isTilted());
        telemetry.addData("IMU (Still)", isStill());

        telemetry.addData("Drive (Pose)", config.pose.toString());

        telemetry.addData("Drive (LF)", "%.2f pow, %d pos, %.2f dist", hardware.driveLeftFront.get(), hardware.driveLeftFront.getCurrentPosition(), hardware.driveLeftFront.getDistance());
        telemetry.addData("Drive (RF)", "%.2f pow, %d pos, %.2f dist", hardware.driveRightFront.get(), hardware.driveRightFront.getCurrentPosition(), hardware.driveRightFront.getDistance());
        telemetry.addData("Drive (LR)", "%.2f pow, %d pos, %.2f dist", hardware.driveLeftRear.get(), hardware.driveLeftRear.getCurrentPosition(), hardware.driveLeftRear.getDistance());
        telemetry.addData("Drive (RR)", "%.2f pow, %d pos, %.2f dist", hardware.driveRightRear.get(), hardware.driveRightRear.getCurrentPosition(), hardware.driveRightRear.getDistance());

        vuforia.update();

        if (vuforia.targetVisible) {
            navPoseRaw = new Pose2d(
                vuforia.translation.get(0) / mmPerInch,
                vuforia.translation.get(1) / mmPerInch,
                vuforia.rotation.thirdAngle
            );

            if (config.auto && !config.started) {
                config.alliance = vuforia.targetAlliance;
                config.side = vuforia.targetSide;
            }

            double distance = Math.hypot(
                vuforia.targetPose.getX() - config.pose.getX(),
                vuforia.targetPose.getY() - config.pose.getY()
            );

            if (isStill() && (!config.started || distance < TILE_WIDTH * 2)) {
                navPoseSum[0] += navPoseRaw.getX();
                navPoseSum[1] += navPoseRaw.getY();
                navPoseSum[2] += navPoseRaw.getHeading();
                navPoseSum[3]++;

                if (navPoseSum[3] >= config.navSamples) {
                    odometry.setPoseEstimate(
                        config.pose = navPoseAvg = new Pose2d(
                            navPoseSum[0] / navPoseSum[3],
                            navPoseSum[1] / navPoseSum[3],
                            navPoseSum[2] / navPoseSum[3]
                        )
                    );

                    vuforia.setCameraPosition();

                    config.lightingCurrent = GREEN;
                    navPoseSum = new double[4];
                }
            }
        } else {
            config.lightingCurrent = BLACK;
            navPoseSum = new double[4];
        }

         telemetry.addData("Nav (Target)", vuforia.targetName);
         telemetry.addData("Nav (Count)", vuforia.targetCount);
         telemetry.addData("Nav (Pose Raw)", navPoseRaw);
         telemetry.addData("Nav (Pose Avg)", navPoseAvg);
    }

    public void inputs(double strafe, double forward, double turn) {
        if (isTilted()) strafe = forward = turn = 0;
        if (strafe + forward + turn != 0) odometry.followTrajectorySequenceAsync(null);
        else if (odometry.isBusy()) return;
        strafe *= power; forward *= power; turn *= power;
        if (DRIVE_FIELD_CENTRIC) drive.driveFieldCentric(strafe, forward, turn, getYaw(), SQUARE_INPUTS);
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

    public double getRoll() {
        return hardware.imuAngles.getRoll(RADIANS);
    }

    public double getPitch() {
        return hardware.imuAngles.getPitch(RADIANS);
    }

    public double getYaw() {
        return hardware.imuAngles.getYaw(RADIANS);
    }

    public double getRollRate() {
        return hardware.imuVelocities.xRotationRate;
    }

    public double getPitchRate() {
        return hardware.imuVelocities.yRotationRate;
    }

    public double getYawRate() {
        return hardware.imuVelocities.zRotationRate;
    }

    public boolean isTilted() {
        return Math.abs(getRoll()) + Math.abs(getPitch()) >= Math.toRadians(ALLOWABLE_TILT);
    }

    public boolean isStill() {
        return Math.abs(getRollRate()) + Math.abs(getPitchRate() + Math.abs(getYawRate())) <= Math.toRadians(ALLOWABLE_STILL);
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

                    double distance = Math.hypot(
                        prev.getX() - curr.getX(),
                        prev.getY() - curr.getY()
                    );

                    double remainder = curr.getHeading() - prev.getHeading();
                    if (remainder > +Math.PI) remainder -= Math.PI * 2;
                    if (remainder < -Math.PI) remainder += Math.PI * 2;

                    if (remainder > +Math.PI * 0.9 || remainder < -Math.PI * 0.9) {
                        curr = poses[i] = new Pose2d(curr.getX(), curr.getY(), prev.getHeading());
                        builder.lineToConstantHeading(new Vector2d(curr.getX(), curr.getY()));
                    } else {
                        builder.turn(remainder);
                        builder.lineToLinearHeading(curr);
                    }
                }
            }
        );
    }

    public boolean isBusy(double offset) {
        return odometry.isBusy(offset);
    }

    private void followTrajectoryAsync(Consumer<TrajectorySequenceBuilder> consumer) {
        Pose2d current = getPose();
        TrajectorySequenceBuilder builder = odometry.trajectorySequenceBuilder(current);
        consumer.accept(builder);
        odometry.followTrajectorySequenceAsync(builder.build());
    }
}
