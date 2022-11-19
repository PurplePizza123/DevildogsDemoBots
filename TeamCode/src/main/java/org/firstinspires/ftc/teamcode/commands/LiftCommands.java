package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftCommands extends Commands {
    public Command calibrate() {
        return new InstantCommand(subsystems.lift::calibrate, subsystems.lift)/*.andThen(
                wait.until(() -> subsystems.lift.calibrated)
        ).withTimeout(3000)*/;
    }

    public Command up() {
        return new InstantCommand(subsystems.lift::up, subsystems.lift);
    }

    public Command down() {
        return new InstantCommand(subsystems.lift::down, subsystems.lift);
    }

    public Command to(LiftSubsystem.LiftPosition height, double offset) {
        return new InstantCommand(() -> subsystems.lift.to(height, offset), subsystems.lift);
    }

    public Command to(LiftSubsystem.LiftPosition height) {
        return to(height, 0);
    }
}
