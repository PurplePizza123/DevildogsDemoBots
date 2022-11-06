package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

public class LiftCommands extends Commands {
    public Command up() {
        return new InstantCommand(subsystems.lift::toHalf, subsystems.lift);
    }

    public Command down() {
        return new InstantCommand(subsystems.lift::toBottom, subsystems.lift);
    }

    public Command stop() {
        return new RunCommand(subsystems.lift::stop, subsystems.lift);
    }
}
