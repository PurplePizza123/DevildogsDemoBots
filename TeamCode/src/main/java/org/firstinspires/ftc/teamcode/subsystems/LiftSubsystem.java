package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class LiftSubsystem extends HardwareSubsystem {
    public static double SPOOL_CIRCUMFERENCE = 4.409;
    public static double PULSES_PER_REVOLUTION = 384.5;
    public static double HEIGHT_PER_PULSE = SPOOL_CIRCUMFERENCE / PULSES_PER_REVOLUTION;
    public static double POWER = 1.0;
    public static double MIN = 0;
    public static double MAX = 33.5;

    public boolean calibrated = false;
    public double height = MIN;

    public LiftSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        hardware.lift.motor.setMode(RUN_USING_ENCODER);
    }

    @Override
    public void periodic() {
        telemetry.addData("Lift","%.2f pow, %.2f height, %b left, %b right", hardware.lift.motor.getPower(), hardware.lift.getCurrentPosition() * HEIGHT_PER_PULSE, !hardware.liftLeftLimit.getState(), !hardware.liftRightLimit.getState());

        if (calibrated) return;

        boolean isDown = !hardware.liftLeftLimit.getState() || !hardware.liftRightLimit.getState();

        if (isDown) {
            hardware.lift.motor.setMode(STOP_AND_RESET_ENCODER);
            hardware.lift.motor.setTargetPosition(0);
            hardware.lift.motor.setMode(RUN_TO_POSITION);
            hardware.lift.motor.setPower(+POWER);
            calibrated = true;
            height = MIN;
        }
    }

    public void calibrate() {
        calibrated = false;
        hardware.lift.motor.setMode(RUN_USING_ENCODER);
        hardware.lift.motor.setPower(-POWER);
    }

    public void change(double offset) {
        to(height + offset);
    }

    public void to(double height) {
        height = clamp(height, MIN, MAX);
        hardware.lift.motor.setPower(height > this.height ? +POWER : -POWER);
        hardware.lift.motor.setTargetPosition(
            (int)(((this.height = height) - MIN) / HEIGHT_PER_PULSE)
        );
    }
}
