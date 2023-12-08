package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class DepositSubsystem extends SubsystemBase {
    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Deposit",
            () -> String.format(
                "%.1f pow",
                hardware.deposit.getPosition()
            )
        );
    }

    public void open() {
        hardware.deposit.setPosition(0.79);
    }

    public void closed() {
        hardware.deposit.setPosition(0.12);
    }
}
