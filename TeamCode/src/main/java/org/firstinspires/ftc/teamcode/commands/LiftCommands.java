package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class LiftCommands {
    public Command change(double offset) {
        return new InstantCommand(
            () -> Subsystems.lift.change(offset),
            Subsystems.lift
        );
    }

    public Command to(double height) {
        return new InstantCommand(
            () -> Subsystems.lift.to(height),
            Subsystems.lift
        );
    }
}
