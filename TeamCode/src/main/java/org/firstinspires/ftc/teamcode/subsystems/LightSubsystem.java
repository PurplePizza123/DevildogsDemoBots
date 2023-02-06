package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLACK;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.RED;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class LightSubsystem extends HardwareSubsystem {
    public LightSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        if (config.started && config.timer.seconds() >= 80)
            config.lightingDefault = RED;

        hardware.lights.setPattern(
            config.lightingCurrent == BLACK ?
                config.lightingDefault :
                config.lightingCurrent
        );
    }
}
