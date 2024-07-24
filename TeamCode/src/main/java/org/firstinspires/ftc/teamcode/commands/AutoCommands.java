package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.drive;

import com.arcrobotics.ftclib.command.Command;

public class AutoCommands {
    public Command execute() {
        return courseA();
    }

    public Command courseA() {
        return drive.setDrivePower(25).andThen(

        );
    }
}
