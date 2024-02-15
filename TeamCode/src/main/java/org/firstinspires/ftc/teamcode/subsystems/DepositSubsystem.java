package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class DepositSubsystem extends SubsystemBase {

    public static double OPEN_POSITION = .6;
    public static double CLOSED_POSITION = .5;

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Deposit",
            () -> String.format(
                "%.1f pos",
                hardware.deposit.getPosition()
            )
        );
    }

    public void open() {
        hardware.deposit.setPosition(OPEN_POSITION);
    }

    public void closed() {
        hardware.deposit.setPosition(CLOSED_POSITION);
    }
}
