package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PeriodicSubsystem extends SubsystemBase {
    private final ElapsedTime periodicTimer = new ElapsedTime();
    private final ElapsedTime runtimeTimer = new ElapsedTime();
    private int periodicCount;

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        ++periodicCount;

        telemetry.addData(
            "Periodic",
            () -> String.format(
                "%.1fs, %.1fms, %.1fhz",
                config.timer.seconds(),
                periodicTimer.milliseconds(),
                periodicCount / runtimeTimer.seconds()
            )
        );

        periodicTimer.reset();

        telemetry.update();
    }
}
