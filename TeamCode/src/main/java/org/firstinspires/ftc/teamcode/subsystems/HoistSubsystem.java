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
public class HoistSubsystem extends SubsystemBase {
    public static double POWER = .1;
    public static double SPOOL_CIRCUMFERENCE = 63 / 25.3 * Math.PI; //getting in from mm
    public static double PULSES_PER_REVOLUTION = 751.8;
    public static double HEIGHT_PER_PULSE = SPOOL_CIRCUMFERENCE / PULSES_PER_REVOLUTION;
    public static double MIN = 0;
    public static double MAX = 16; // TODO: change max to what max needs to be.
    public static double height = MIN;

    public HoistSubsystem() {
        hardware.hoist.motor.setDirection(REVERSE);
        hardware.hoist.motor.setMode(STOP_AND_RESET_ENCODER);
        hardware.hoist.motor.setTargetPosition(0);
        hardware.hoist.motor.setMode(RUN_TO_POSITION);
        hardware.hoist.motor.setPower(+POWER);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Hoist",
            () -> String.format(
                "%.1f pow, %.1f pow",
                hardware.hoist.get(),
                hardware.hoistHelp.getPower()
            )
        );
    }

    public void up() {
        hardware.hoistHelp.setPower(+POWER);
    }

    public void stop() {
        hardware.hoistHelp.setPower(0);
    }

    public void change(double offset) {
        to(height + offset);
    }

    public void to(double height) {
        height = clamp(height, MIN + 0.25, MAX);
        hardware.hoist.motor.setPower(height > this.height ? +POWER : -POWER);
        hardware.hoist.motor.setTargetPosition(
                (int)(((this.height = height) - MIN) / HEIGHT_PER_PULSE)
        );
    }
}
