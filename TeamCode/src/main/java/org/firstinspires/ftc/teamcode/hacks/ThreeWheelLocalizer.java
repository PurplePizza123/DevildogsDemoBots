package org.firstinspires.ftc.teamcode.hacks;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;

import org.firstinspires.ftc.teamcode.Hardware;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class ThreeWheelLocalizer extends ThreeTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 8192;
    public static double WHEEL_RADIUS = 0.68897638; // in
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed
    public static double LATERAL_DISTANCE = 13.2500; // in; distance between the left and right wheels
    public static double LATERAL_MULTIPLIER = LATERAL_DISTANCE / 13.3126;
    public static double FORWARD_OFFSET = -5.0000; // in; offset of the lateral wheel
    public static double X_MULTIPLIER = 1.0121242485738; // Multiplier in the X direction
    public static double Y_MULTIPLIER = 1.01830557524067; // Multiplier in the Y direction

    private final Hardware hardware;

    public ThreeWheelLocalizer(Hardware hardware) {
        super(Arrays.asList(
            new Pose2d(-1.5000, +6.4375 * LATERAL_MULTIPLIER, 0), // left
            new Pose2d(-1.5000, -6.8125 * LATERAL_MULTIPLIER, 0), // right
            new Pose2d(-5.0000, -0.0625, Math.toRadians(90)) // perpendicular
        ));

        this.hardware = hardware;
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
            encoderTicksToInches(hardware.odometryLeft.getCurrentPosition()) * X_MULTIPLIER,
            encoderTicksToInches(hardware.odometryRight.getCurrentPosition()) * X_MULTIPLIER,
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
            encoderTicksToInches(hardware.odometryRight.getCorrectedVelocity()) * X_MULTIPLIER,
            encoderTicksToInches(hardware.odometryCenter.getCorrectedVelocity()) * Y_MULTIPLIER
        );
    }
}
