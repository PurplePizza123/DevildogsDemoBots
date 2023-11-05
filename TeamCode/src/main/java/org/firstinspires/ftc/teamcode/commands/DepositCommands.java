package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class DepositCommands extends Commands {
    public Command open() {
        return new InstantCommand(subsystems.deposit::open, subsystems.deposit);
    }

    public Command close() {
        return new InstantCommand(subsystems.sweeper::out, subsystems.sweeper);
    }

    public Command stop() {
        return new InstantCommand(subsystems.sweeper::stop, subsystems.sweeper);
    }
}
