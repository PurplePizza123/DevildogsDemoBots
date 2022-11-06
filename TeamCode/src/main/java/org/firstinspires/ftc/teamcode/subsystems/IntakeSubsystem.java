package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class IntakeSubsystem extends HardwareSubsystem{
    public static double POWER = 1;
    public static String STATE = null;

    public IntakeSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData("Intake", "%.2f vel, %s", hardware.intake.get(), STATE);
    }

    public void in() {
        STATE = "IN";
        hardware.intake.set(POWER);
    }

    public void out() {
        STATE = "OUT";
        hardware.intake.set(-POWER);
    }

    public void stop() {
        STATE = "STOP";
        hardware.intake.set(0);
    }
}
