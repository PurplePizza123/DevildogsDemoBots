package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad1;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.ConfigThread;

public class MenuSubsystem extends HardwareSubsystem {
    public MenuSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        new ConfigThread().start();
    }

    @Override
    public void periodic() {
        if (!config.auto) return;

        telemetry.addData(
            "Menu", "%s %s, %.1fx, %.1fy, %d stacks, %.1fs delay",
            config.alliance,
            config.side,
            config.offsetX,
            config.offsetY,
            config.stacks,
            config.delay
        );
    }
}
