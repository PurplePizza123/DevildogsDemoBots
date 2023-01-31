package org.firstinspires.ftc.teamcode.hacks;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.TwoTrackingWheelLocalizer;

import org.firstinspires.ftc.teamcode.Hardware;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    ^
 *    |
 *    | ( x direction)
 *    |
 *    v
 *    <----( y direction )---->

 *        (forward)
 *    /--------------\
 *    |     ____     |
 *    |     ----     |    <- Perpendicular Wheel
 *    |           || |
 *    |           || |    <- Parallel Wheel
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class TwoWheelLocalizer extends TwoTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 8192;
    public static double WHEEL_RADIUS = 0.68897638; // in
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed
    public static double PARALLEL_LEFT_X = -1.5000; // X is the forward direction
    public static double PARALLEL_LEFT_Y = +6.4375; // Y is the strafe direction
    public static double PARALLEL_RIGHT_X = -1.5000; // X is the forward direction
    public static double PARALLEL_RIGHT_Y = -6.8125; // Y is the strafe direction
    public static double PERPENDICULAR_X = -5.0000;
    public static double PERPENDICULAR_Y = -0.0625;
    public static double X_MULTIPLIER = 1.0121242485738; // Multiplier in the X direction
    public static double Y_MULTIPLIER = 1.01830557524067; // Multiplier in the Y direction

    private final Hardware hardware;
    private final Odometry odometry;

    public TwoWheelLocalizer(Hardware hardware, Odometry odometry) {
        super(Arrays.asList(
            new Pose2d(PARALLEL_LEFT_X, PARALLEL_LEFT_Y, 0),
            new Pose2d(PERPENDICULAR_X, PERPENDICULAR_Y, Math.toRadians(90))
        ));

        this.hardware = hardware;
        this.odometry = odometry;
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @Override
    public double getHeading() {
        return odometry.getRawExternalHeading();
    }

    @Override
    public Double getHeadingVelocity() {
        return odometry.getExternalHeadingVelocity();
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
            encoderTicksToInches(hardware.odometryLeft.getCurrentPosition()) * X_MULTIPLIER,
            encoderTicksToInches(hardware.odometryCenter.getCurrentPosition()) * Y_MULTIPLIER
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        // If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        // competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        // compensation method
        return Arrays.asList(
            encoderTicksToInches(hardware.odometryLeft.getCorrectedVelocity()) * X_MULTIPLIER,
            encoderTicksToInches(hardware.odometryCenter.getCorrectedVelocity()) * Y_MULTIPLIER
        );
    }
}
