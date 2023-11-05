package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.auto;
import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.commands.Commands.rand;

import com.arcrobotics.ftclib.command.Command;

public class AutoCommands {
    public Command execute() {
        return lift.calibrate().andThen(
            rand.detect(),
            auto.park()
        );
    }

    public Command park() {
        return null;
    }
}
