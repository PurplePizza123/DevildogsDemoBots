package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class ArmSubsystem extends SubsystemBase {

    public static double UP_POSITION = .6;
    public static double DOWN_POSITION = .5;

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

    public void up() {
        hardware.deposit.setPosition(UP_POSITION);
    }

    public void down() {
        hardware.deposit.setPosition(DOWN_POSITION);
    }
}
