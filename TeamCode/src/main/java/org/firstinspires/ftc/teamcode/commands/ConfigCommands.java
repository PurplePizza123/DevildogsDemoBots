package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.config;
import static org.firstinspires.ftc.teamcode.game.Parking.INNER;
import static org.firstinspires.ftc.teamcode.game.Parking.OUTER;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.ConfigSubsystem;


public class ConfigCommands {
    public Command setEditable(boolean editable) {
        return new InstantCommand(
            () -> config.setEditable(editable), config
        );
    }

    public Command changeItem(ConfigSubsystem.Change change) {
        return new InstantCommand(
            () -> config.changeItem(change), config
        );
    }

    public Command changeValue(ConfigSubsystem.Change change) {
        return new InstantCommand(
            () -> config.changeValue(change), config
        );
    }
}