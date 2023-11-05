package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class DepositCommands {
    public Command open() {
        return new InstantCommand(Subsystems.deposit::open, Subsystems.deposit);
    }

    public Command close() {
        return new InstantCommand(Subsystems.sweeper::out, Subsystems.sweeper);
    }

    public Command stop() {
        return new InstantCommand(Subsystems.sweeper::stop, Subsystems.sweeper);
    }
}
