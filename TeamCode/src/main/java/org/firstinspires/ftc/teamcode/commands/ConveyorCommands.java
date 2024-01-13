package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class ConveyorCommands {
    public Command in() {
        return new InstantCommand(
            Subsystems.conveyor::in,
            Subsystems.conveyor
        );
    }

    public Command in(double power) {
        return new InstantCommand(
            () -> Subsystems.conveyor.in(power),
            Subsystems.conveyor
        );
    }

    public Command out() {
        return new InstantCommand(
            Subsystems.conveyor::out,
            Subsystems.conveyor
        );
    }

    public Command out(double power) {
        return new InstantCommand(
            () -> Subsystems.conveyor.out(power),
            Subsystems.conveyor
        );
    }

    public Command stop() {
        return new InstantCommand(
            Subsystems.conveyor::stop,
            Subsystems.conveyor
        );
    }
}
