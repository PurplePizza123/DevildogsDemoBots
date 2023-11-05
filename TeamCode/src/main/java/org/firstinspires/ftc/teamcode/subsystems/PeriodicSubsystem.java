package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PeriodicSubsystem extends SubsystemBase {
    private int periodicCount;
    private ElapsedTime periodicTimer = new ElapsedTime();
    private ElapsedTime runtimeTimer = new ElapsedTime();

    @Override
    public void periodic() {
        telemetry.addData(
            "Periodic", "%.1fs, %.1fms, %.1fhz",
            config.timer.seconds(),
            periodicTimer.milliseconds(),
            ++periodicCount / runtimeTimer.seconds()
        );

        telemetry.update();

        periodicTimer.reset();
    }
}
