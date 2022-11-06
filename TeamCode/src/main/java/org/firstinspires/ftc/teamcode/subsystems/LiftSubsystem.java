package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class LiftSubsystem extends HardwareSubsystem{

    private static final double LIFT_SPOOL_CIRCUMFERENCE_IN = 4.80315;
    private static final double LIFT_PULSES_PER_REVOLUTION = 384.5;
    private static final double LIFT_INCHES_PER_PULSE = LIFT_SPOOL_CIRCUMFERENCE_IN/LIFT_PULSES_PER_REVOLUTION;

    public LiftSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        hardware.lift.setDistancePerPulse(LIFT_INCHES_PER_PULSE);
    }

    public void up() {
        hardware.lift.set(0.1);
    }

    public void down() {
        hardware.lift.set(-0.1);
    }

    public void stop() {
        hardware.lift.set(0);
    }

    public void to(double inches){
        hardware.lift.setTargetDistance(inches);
        hardware.lift.setRunMode(Motor.RunMode.PositionControl);
        hardware.lift.set(.1);
    }
}
