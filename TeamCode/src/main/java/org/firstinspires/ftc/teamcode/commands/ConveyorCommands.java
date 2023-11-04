package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class ConveyorCommands extends Commands {
    public Command in() {
        return new InstantCommand(subsystems.conveyor::in, subsystems.conveyor);
    }

    public Command out() {
        return new InstantCommand(subsystems.conveyor::out, subsystems.conveyor);
    }

    public Command stop() {
        return new InstantCommand(subsystems.conveyor::stop, subsystems.conveyor);
    }
}
