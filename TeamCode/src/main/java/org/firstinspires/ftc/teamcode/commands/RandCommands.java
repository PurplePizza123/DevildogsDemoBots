package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class RandCommands extends Commands {
    public Command detect() {
        return wait.seconds(config.delay).andThen(
            new InstantCommand(subsystems.rand::disable)
        );
    }
}
