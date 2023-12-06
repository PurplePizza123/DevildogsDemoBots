package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class HoistSubsystem extends SubsystemBase {
    public static double POWER = 1;

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Hoist",
            () -> String.format(
                "%.1f pow",
                hardware.hoist.get()
            )
        );
    }

    public void up() {
        hardware.hoist.set(+POWER);
    }

    public void down() {
        hardware.hoist.set(-POWER);
    }

    public void stop() {
        hardware.hoist.set(0);
    }
}
