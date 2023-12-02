package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class MenuSubsystem extends SubsystemBase {
    @Override
    public void periodic() {
        if (!config.auto) return;

        telemetry.addData(
            "Menu", "%s %s, %.1fx, %.1fy, %.1fs delay, stacks: %d, detection: %d",
            config.alliance,
            config.side,
            config.offsetX,
            config.offsetY,
            config.delay,
            config.stacks,
            config.detection
        );
    }
}
