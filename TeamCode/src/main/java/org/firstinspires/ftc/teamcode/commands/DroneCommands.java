package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class DroneCommands {
    public Command lock() {
        return new InstantCommand(Subsystems.drone::lock, Subsystems.drone);
    }

    public Command release() {
        return new InstantCommand(Subsystems.drone::release, Subsystems.drone).andThen(
            wait.seconds(1),
            lock()
        );
    }
}
