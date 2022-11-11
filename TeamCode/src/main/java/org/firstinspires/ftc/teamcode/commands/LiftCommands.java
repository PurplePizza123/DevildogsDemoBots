package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftHeight.GROUND;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftCommands extends Commands {
    public Command up() {
        return new InstantCommand(subsystems.lift::up, subsystems.lift);
    }

    public Command down() {
        return new InstantCommand(subsystems.lift::down, subsystems.lift);
    }

    public Command to(LiftSubsystem.LiftHeight height) {
        return new InstantCommand(() -> subsystems.lift.to(height), subsystems.lift);
    }
}
