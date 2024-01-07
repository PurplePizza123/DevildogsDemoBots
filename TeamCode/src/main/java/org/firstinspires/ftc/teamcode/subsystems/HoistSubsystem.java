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
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class HoistSubsystem extends SubsystemBase {
    public static double SERVO_POWER = 1.0;
    public static double MOTOR_POWER = 1.0;
    public static double SPOOL_CIRCUMFERENCE = 63 / 25.3 * Math.PI;
    public static double PULSES_PER_REVOLUTION = 751.8;
    public static double HEIGHT_PER_PULSE = SPOOL_CIRCUMFERENCE / PULSES_PER_REVOLUTION;
    public static double MIN = 0;
    public static double MAX = 18;
    public static double height = MIN;
    public static ElapsedTime atPositionTimer = new ElapsedTime();

    public HoistSubsystem() {
        hardware.hoist.motor.setDirection(REVERSE);
        hardware.hoist.motor.setMode(STOP_AND_RESET_ENCODER);
        hardware.hoist.motor.setTargetPosition(0);
        hardware.hoist.motor.setMode(RUN_TO_POSITION);
        hardware.hoist.motor.setPower(+MOTOR_POWER);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        if (hardware.hoist.atTargetPosition() && atPositionTimer.seconds() >= 1)
            hardware.hoistHelp.setPower(0);

        telemetry.addData(
            "Hoist",
            () -> String.format(
                "%.1f pow, %.1f pow, %.1f height",
                hardware.hoistHelp.getPower(),
                hardware.hoist.get(),
                hardware.hoist.getCurrentPosition() * HEIGHT_PER_PULSE
            )
        );
    }

    public void change(double offset) {
        to(height + offset);
    }

    public void to(double height) {
        atPositionTimer.reset();
        height = clamp(height, MIN + 0.25, MAX);
        if (height > this.height)
            hardware.hoistHelp.setPower(SERVO_POWER);
        hardware.hoist.motor.setPower(height > this.height ? +MOTOR_POWER : -MOTOR_POWER);
        hardware.hoist.motor.setTargetPosition(
            (int)(((this.height = height) - MIN) / HEIGHT_PER_PULSE)
        );
    }
}
