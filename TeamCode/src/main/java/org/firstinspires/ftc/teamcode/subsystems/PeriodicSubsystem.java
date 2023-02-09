package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class PeriodicSubsystem extends HardwareSubsystem {
    private int periodicCount;
    private ElapsedTime periodicTimer = new ElapsedTime();
    private ElapsedTime runtimeTimer = new ElapsedTime();

    public PeriodicSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

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
