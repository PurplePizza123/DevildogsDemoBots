package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class LiftSubsystem extends HardwareSubsystem {
    public static double LIFT_SPOOL_CIRCUMFERENCE = 4.80315;
    public static double LIFT_PULSES_PER_REVOLUTION = 384.5;
    public static double LIFT_INCHES_PER_PULSE = LIFT_SPOOL_CIRCUMFERENCE / LIFT_PULSES_PER_REVOLUTION;
    public static double POWER = 1.0;
    public static double MIN = 0;
    public static double MAX = 32;
    public static double INCREMENT = 8;
    private static double HEIGHT = 0;

    public LiftSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        hardware.lift.setMode(STOP_AND_RESET_ENCODER);
        hardware.lift.setTargetPosition(0);
        hardware.lift.setMode(RUN_TO_POSITION);
        hardware.lift.setPower(POWER);
    }

    @Override
    public void periodic() {
        telemetry.addData("Lift","%.2f pow, %.2f height", hardware.lift.getPower(), hardware.lift.getCurrentPosition() * LIFT_INCHES_PER_PULSE);
        telemetry.update();
    }

    public void up() {
        if (HEIGHT < MAX) to(HEIGHT += INCREMENT);
    }

    public void down() {
        if (HEIGHT > MIN) to(HEIGHT -= INCREMENT);
    }

    public void to(double inches) {
        hardware.lift.setTargetPosition(
            (int)(inches / LIFT_INCHES_PER_PULSE)
        );
    }
}
