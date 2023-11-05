package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class SweeperCommands extends Commands {
    public Command in() {
        return new InstantCommand(subsystems.sweeper::in, subsystems.sweeper);
    }

    public Command out() {
        return new InstantCommand(subsystems.sweeper::out, subsystems.sweeper);
    }

    public Command stop() {
        return new InstantCommand(subsystems.sweeper::stop, subsystems.sweeper);
    }
}
