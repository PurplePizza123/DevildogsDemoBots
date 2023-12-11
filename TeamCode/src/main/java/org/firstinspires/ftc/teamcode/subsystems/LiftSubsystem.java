package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class LiftSubsystem extends SubsystemBase {
    public static double SPOOL_CIRCUMFERENCE = 1.5748 * Math.PI;
    public static double PULSES_PER_REVOLUTION = 1425.1;
    public static double HEIGHT_PER_PULSE = SPOOL_CIRCUMFERENCE / PULSES_PER_REVOLUTION;
    public static double POWER = 1.0;
    public static double MIN = 0;
    public static double MAX = 30; // TODO: change max to what max needs to be.

    public double height = MIN;

    public LiftSubsystem() {
        hardware.lift.motor.setDirection(REVERSE);
        hardware.lift.motor.setMode(STOP_AND_RESET_ENCODER);
        hardware.lift.motor.setTargetPosition(0);
        hardware.lift.motor.setMode(RUN_TO_POSITION);
        hardware.lift.motor.setPower(+POWER);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Lift",
            () -> String.format(
                "%.1f pow, %.1f height",
                hardware.lift.motor.getPower(),
                hardware.lift.getCurrentPosition() * HEIGHT_PER_PULSE
            )
        );
    }

    public void change(double offset) {
        to(height + offset);
    }

    public void to(double height) {
        height = clamp(height, MIN + 0.25, MAX);
        hardware.lift.motor.setPower(height > this.height ? +POWER : -POWER);
        hardware.lift.motor.setTargetPosition(
            (int)(((this.height = height) - MIN) / HEIGHT_PER_PULSE)
        );
    }
}
