package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.PositionControl;
import static com.arcrobotics.ftclib.util.MathUtils.clamp;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class LiftSubsystem extends SubsystemBase {
    public static double SPOOL_CIRCUMFERENCE = 1.495777778 * Math.PI;
    public static double POWER = 1;
    public static double MIN = 0;
    public static double MAX = 28;
    public static double PIXEL_HEIGHT = 3.3125;
    public static double INITIAL_BACKDROP_HEIGHT = 0;

    public double height = MIN;

    public LiftSubsystem() {
        hardware.lift.stopAndResetEncoder();
        hardware.lift.setInverted(true);
        hardware.lift.setRunMode(PositionControl);
        hardware.lift.setDistancePerRevolution(SPOOL_CIRCUMFERENCE);
        hardware.lift.setTargetDistance(0);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        hardware.lift.set(POWER);
        telemetry.addData(
            "Lift",
            () -> String.format(
                "%.1f pow, %.1f height",
                hardware.lift.get(),
                hardware.lift.getDistance()
            )
        );
    }

    public void change(double offset) {
        to(height + offset);
    }

    public void to(double height) {
        hardware.lift.setTargetDistance(
            this.height = clamp(height, MIN, MAX)
        );
    }

    public void toScorePos() {
        hardware.lift.setTargetDistance((PIXEL_HEIGHT * config.backdropRow) + INITIAL_BACKDROP_HEIGHT);
    }
}
