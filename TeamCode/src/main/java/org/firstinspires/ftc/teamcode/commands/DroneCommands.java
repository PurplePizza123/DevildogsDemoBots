package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class DroneCommands {
    public Command release() {
        return open().andThen(
            wait.seconds(1),
            close()
        );
    }

    public Command open() {
        return new InstantCommand(
            Subsystems.drone::open,
            Subsystems.drone
        );
    }

    public Command close() {
        return new InstantCommand(
            Subsystems.drone::close,
            Subsystems.drone
        );
    }
}
