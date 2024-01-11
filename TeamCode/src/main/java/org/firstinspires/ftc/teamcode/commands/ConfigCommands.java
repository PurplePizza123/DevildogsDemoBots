package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.ConfigSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class ConfigCommands {
    public Command setEditable(boolean editable) {
        return new InstantCommand(
            () -> Subsystems.config.setEditable(editable),
            Subsystems.config
        );
    }

    public Command changeItem(ConfigSubsystem.Change change) {
        return new InstantCommand(
            () -> Subsystems.config.changeItem(change),
            Subsystems.config
        );
    }

    public Command changeValue(ConfigSubsystem.Change change) {
        return new InstantCommand(
            () -> Subsystems.config.changeValue(change),
            Subsystems.config
        );
    }
}
