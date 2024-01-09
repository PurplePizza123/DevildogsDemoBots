package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.vision;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.adaptations.roadrunner.OmniDrive;
import org.firstinspires.ftc.teamcode.adaptations.roadrunner.Trajectory;

import java.util.function.Consumer;

@Config
public class DriveSubsystem extends SubsystemBase {
    public static double DISTANCE_PER_REVOLUTION = 3.78 * Math.PI;
    public static double ALLOWABLE_TILT = 10;
    public static double ALLOWABLE_STILL = 1.0;

    public double power = 0.5;

    private Action trajectoryAction = null;

    private final OmniDrive drive;

    public DriveSubsystem() {
        drive = new OmniDrive(hardwareMap, config.pose);
        hardware.drive.setDistancePerRevolution(DISTANCE_PER_REVOLUTION);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        hardware.clearBulkCache();

        if (isTilted() || (config.auto && config.timer.seconds() > 29.9)) {
            trajectoryAction = null;
            inputs(0,0,0);
        }

        if (trajectoryAction != null) {
            if (!trajectoryAction.run(
                new TelemetryPacket()
            )) trajectoryAction = null;
        }

        config.pose = getPose();

        if (vision.detectionPose != null && isStill()) {
            drive.pose = vision.detectionPose;
        }

        telemetry.addData("Drive (Pose)", () -> String.format("%.1fx, %.1fy, %.1f°", config.pose.position.x, config.pose.position.y, Math.toDegrees(config.pose.heading.toDouble())));

        telemetry.addData("Drive (FL)", () -> String.format("%.1f pow, %d pos, %.1f vel, %.1f dist", hardware.driveFrontLeft.get(), hardware.driveFrontLeft.getCurrentPosition(), hardware.driveFrontLeft.getVelocity(), hardware.driveFrontLeft.getDistance()));
        telemetry.addData("Drive (FR)", () -> String.format("%.1f pow, %d pos, %.1f vel, %.1f dist", hardware.driveFrontRight.get(), hardware.driveFrontRight.getCurrentPosition(), hardware.driveFrontRight.getVelocity(), hardware.driveFrontRight.getDistance()));
        telemetry.addData("Drive (BL)", () -> String.format("%.1f pow, %d pos, %.1f vel, %.1f dist", hardware.driveBackLeft.get(), hardware.driveBackLeft.getCurrentPosition(), hardware.driveBackLeft.getVelocity(), hardware.driveBackLeft.getDistance()));
        telemetry.addData("Drive (BR)", () -> String.format("%.1f pow, %d pos, %.1f vel, %.1f dist", hardware.driveBackRight.get(), hardware.driveBackRight.getCurrentPosition(), hardware.driveBackRight.getVelocity(), hardware.driveBackRight.getDistance()));

        telemetry.addData("Drive (OR)", () -> String.format("%d pos, %d vel", hardware.odometryRight.getPositionAndVelocity().position, hardware.odometryRight.getPositionAndVelocity().velocity));
        telemetry.addData("Drive (OC)", () -> String.format("%d pos, %d vel", hardware.odometryCenter.getPositionAndVelocity().position, hardware.odometryCenter.getPositionAndVelocity().velocity));

        telemetry.addData("IMU (Roll)", () -> String.format("%.1f°, %.1f°/s", Math.toDegrees(getRoll()), Math.toDegrees(getRollRate())));
        telemetry.addData("IMU (Pitch)", () -> String.format("%.1f°, %.1f°/s", Math.toDegrees(getPitch()), Math.toDegrees(getPitchRate())));
        telemetry.addData("IMU (Yaw)", () -> String.format("%.1f°, %.1f°/s", Math.toDegrees(getYaw()), Math.toDegrees(getYawRate())));
        telemetry.addData("IMU (Tilted)", () -> String.format("%s", isTilted()));
        telemetry.addData("IMU (Still)", () -> String.format("%s", isStill()));
    }

    public void inputs(double forward, double strafe, double turn) {
        if (isTilted()) forward = strafe = turn = 0;
        if (forward + strafe + turn != 0) trajectoryAction = null;
        else if (isBusy()) return;
        forward *= power; strafe *= power; turn *= power;
        drive.setDrivePowers(forward, -strafe, -turn);
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
        drive.updatePoseEstimate();
        return drive.pose;
    }

    public void setPose(Pose2d pose) {
        drive.pose = pose;
    }

    public boolean isBusy() {
        return trajectoryAction != null;
    }

    public void followTrajectoryAsync(Consumer<Trajectory> consumer) {
        Pose2d current = getPose();
        TrajectoryActionBuilder builder = drive.actionBuilder(current);
        Trajectory trajectory = new Trajectory(builder);
        consumer.accept(trajectory);
        trajectoryAction = trajectory.builder.build();
    }
}
