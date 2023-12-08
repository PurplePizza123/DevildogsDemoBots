package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class DepositSubsystem extends SubsystemBase {
    public static double SERVO_POSITION = 0;
    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        hardware.deposit.setPosition(SERVO_POSITION);
        telemetry.addData(
            "Deposit",
            () -> String.format(
                "%.1f pow",
                hardware.deposit.getPosition()
            )
        );
    }

    public void open() {
        // TODO: find servo open position
        hardware.deposit.setPosition(0.12);
    }

    public void closed() {
        // TODO: find servo closed position
        hardware.deposit.setPosition(0.79);
    }
}
