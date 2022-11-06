package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class IntakeSubsystem extends HardwareSubsystem{
    public static double POWER = 1;

    public IntakeSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData("Intake", "%.2f vel", hardware.intake.get())
    }

    public void in() {
        hardware.intake.set(POWER);
    }

    public void out() {
        hardware.intake.set(-POWER);
    }

    public void stop() {
        hardware.intake.set(0);
    }
}
