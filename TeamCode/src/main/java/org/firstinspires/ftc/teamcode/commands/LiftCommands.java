package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class LiftCommands extends Commands {
    public Command up() {
        return new InstantCommand(subsystems.lift::up, subsystems.lift);
    }

    public Command down() {
        return new InstantCommand(subsystems.lift::down, subsystems.lift);
    }

    public Command stop() {
        return new InstantCommand(subsystems.lift::stop, subsystems.lift);
    }
}
