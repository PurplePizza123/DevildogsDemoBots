package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;

public class AutoCommands {
    public Command execute() {
        return wait.seconds(0);
    }
}
