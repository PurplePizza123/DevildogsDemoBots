package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class BucketCommands {
    public Command intake() {
        return new InstantCommand(
            Subsystems.bucket::intake,
            Subsystems.bucket
        );
    }

    public Command deposit() {
        return new InstantCommand(
            Subsystems.bucket::deposit,
            Subsystems.bucket
        );
    }
}
