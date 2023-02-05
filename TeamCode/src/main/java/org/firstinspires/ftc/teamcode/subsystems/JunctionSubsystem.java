package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.game.Junction;

public class JunctionSubsystem extends HardwareSubsystem {
    public JunctionSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        if (config.auto) return;

        for (String line : Junction.lines(config.junction)) {
            telemetry.addLine("<font face=\"monospace\">" + line.replace(" ", "&nbsp;") + "</font>");
        }
    }
}
