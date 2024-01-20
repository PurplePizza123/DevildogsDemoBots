package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class BucketSubsystem extends SubsystemBase {

    public static double INTAKE = 0.4;
    public static double DEPOSIT = 0.6;

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Bucket",
            () -> String.format(
                "%.1f pos",
                hardware.bucket.getPosition()
            )
        );
    }

    public void intake() {
        hardware.bucket.setPosition(INTAKE);
    }

    public void deposit() {
        hardware.bucket.setPosition(DEPOSIT);
    }
}
