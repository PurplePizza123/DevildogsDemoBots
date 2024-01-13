package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.PositionControl;
import static com.arcrobotics.ftclib.util.MathUtils.clamp;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class HoistSubsystem extends SubsystemBase {
    public static double SERVO_POWER = 1;
    public static double MOTOR_POWER = 1;
    public static double SPOOL_CIRCUMFERENCE = 2.075 * Math.PI;
    public static double MIN = 0;
    public static double MAX = 16;

    public double height = MIN;
    public ElapsedTime atPositionTimer = new ElapsedTime();

    public HoistSubsystem() {
        hardware.hoist.stopAndResetEncoder();
        hardware.hoist.setInverted(true);
        hardware.hoist.setRunMode(PositionControl);
        hardware.hoist.setDistancePerRevolution(SPOOL_CIRCUMFERENCE);
        hardware.hoist.setTargetDistance(0);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        hardware.hoist.set(MOTOR_POWER);

        if (hardware.hoist.atTargetPosition() || atPositionTimer.seconds() >= 1)
            hardware.hoistHelp.setPower(0);

        telemetry.addData(
            "Hoist",
            () -> String.format(
                "%.1f pow, %.1f pow, %.1f height, at-pos: %b",
                hardware.hoistHelp.getPower(),
                hardware.hoist.get(),
                hardware.hoist.getDistance(),
                hardware.hoist.atTargetPosition()
            )
        );
    }

    public void change(double offset) {
        to(height + offset);
    }

    public void to(double height) {
        atPositionTimer.reset();
        height = clamp(height, MIN, MAX);
        if (height > this.height)
            hardware.hoistHelp.setPower(SERVO_POWER);
        hardware.hoist.setTargetDistance(this.height = height);
    }
}
