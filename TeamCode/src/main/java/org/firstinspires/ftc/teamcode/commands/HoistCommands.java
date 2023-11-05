package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class HoistCommands extends Commands {
    public Command up() {
        return new InstantCommand(subsystems.hoist::up, subsystems.hoist);
    }

    public Command down() {
        return new InstantCommand(subsystems.hoist::down, subsystems.hoist);
    }

    public Command stop() {
        return new InstantCommand(subsystems.hoist::stop, subsystems.hoist);
    }
}
