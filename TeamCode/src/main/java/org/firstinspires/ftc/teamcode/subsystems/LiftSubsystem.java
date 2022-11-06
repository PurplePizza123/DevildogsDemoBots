package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class LiftSubsystem extends HardwareSubsystem{
    public static double LIFT_SPOOL_CIRCUMFERENCE_IN = 4.80315;
    public static double LIFT_PULSES_PER_REVOLUTION = 384.5;
    public static double LIFT_INCHES_PER_PULSE = LIFT_SPOOL_CIRCUMFERENCE_IN/LIFT_PULSES_PER_REVOLUTION;
    public static double POWER = 0.1;

    public LiftSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        hardware.lift.setDistancePerPulse(LIFT_INCHES_PER_PULSE);
    }

    @Override
    public void periodic() {
        telemetry.addData("Lift","%.2f vel, %.2f height", hardware.lift.getVelocity(),hardware.lift.getDistance());
    }

    public void up() {
        hardware.lift.set(POWER);
    }

    public void down() {
        hardware.lift.set(-POWER);
    }

    public void stop() {
        hardware.lift.set(0);
    }

    public void to(double inches){
        hardware.lift.setTargetDistance(inches);
        hardware.lift.setRunMode(Motor.RunMode.PositionControl);
        hardware.lift.set(POWER);
    }
}
