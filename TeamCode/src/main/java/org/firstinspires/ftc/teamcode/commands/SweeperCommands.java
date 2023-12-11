package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class SweeperCommands {
    public Command in() {
        return new InstantCommand(
            Subsystems.sweeper::in,
            Subsystems.sweeper
        );
    }

    public Command out() {
        return new InstantCommand(
            Subsystems.sweeper::out,
            Subsystems.sweeper
        );
    }

    public Command stop() {
        return new InstantCommand(
            Subsystems.sweeper::stop,
            Subsystems.sweeper
        );
    }
}
