package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.game.Junction;

import java.util.Arrays;

public class MenuSubsystem extends HardwareSubsystem {
    public MenuSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        if (config.auto) telemetry.addData("Menu", "%s alliance, %s side, %d stacks, %.1fs delay", config.alliance, config.side, config.stacks, config.delay);
        else Arrays.stream(Junction.lines(config.junction)).forEach(line -> telemetry.addLine("<font face=\"monospace\">" + line.replace(" ", "&nbsp;") + "</font>"));
    }
}
