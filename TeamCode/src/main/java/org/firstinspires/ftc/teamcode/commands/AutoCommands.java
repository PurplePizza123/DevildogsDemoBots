package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;

public class AutoCommands {
    public Command execute() {
        return wait.seconds(config.delay).andThen(
            scorePurplePixel()
        );
    }
    public Command scorePurplePixel() {
        return drive.toSpikeMark().andThen(
            drive.toSpikeMarkTile()
        );
    }
}
