package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class VisionCommands extends Commands {
    public Command detect() {
        return wait.until(
            () -> !subsystems.randomization.getDetectionLabel().equals("none")
        ).withTimeout(3000).alongWith(
            wait.seconds(config.delay)
        ).andThen(
            new InstantCommand(subsystems.randomization::disable)
        );
    }
}
