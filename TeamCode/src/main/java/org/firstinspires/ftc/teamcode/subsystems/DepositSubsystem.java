package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class DepositSubsystem extends SubsystemBase {
    @Override
    public void periodic() {
        telemetry.addData(
            "Deposit", "%.2f vel",
            hardware.deposit.getPosition()
        );
    }

    public void open() {
        // TODO: find servo open position
        hardware.deposit.setPosition(180);
    }

    public void closed() {
        // TODO: find servo closed position
        hardware.deposit.setPosition(0);
    }
}
