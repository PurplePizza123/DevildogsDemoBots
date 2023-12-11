package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.conveyor;
import static org.firstinspires.ftc.teamcode.commands.Commands.sweeper;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class IntakeCommands {
    public Command getPixels() {
        return sweeper.in().andThen(
            conveyor.in(),
            wait.seconds(0), // TODO: wait for signal
            sweeper.out(),
            wait.seconds(1), // TODO: figure out time it takes for a pixel to get out of conveyor
            conveyor.stop(),
            sweeper.stop()
        );
    }

    public Command in() {
        return new InstantCommand(
            Subsystems.conveyor::in,
            Subsystems.conveyor
        );
    }

    public Command out() {
        return new InstantCommand(
            Subsystems.conveyor::out,
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
