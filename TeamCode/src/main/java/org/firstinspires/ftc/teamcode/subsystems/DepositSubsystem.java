package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class DepositSubsystem extends HardwareSubsystem {
    public static double POWER = 1;

    public DepositSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData(
            "Deposit", "%.2f vel",
            hardware.deposit.getPosition()
        );
    }

    public void open() {
        hardware.deposit.setPosition(180); //TODO find servo open position
    }

    public void closed() {
        hardware.deposit.setPosition(0); //TODO find servo closed position
    }
}