package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.game.Junction;

public class LiftCommands extends Commands {
    public Command calibrate() {
        return new InstantCommand(subsystems.lift::calibrate, subsystems.lift).andThen(
            wait.until(() -> subsystems.lift.calibrated).withTimeout(3000)
        );
    }

    public Command up() {
        return new InstantCommand(subsystems.lift::up, subsystems.lift);
    }

    public Command down() {
        return new InstantCommand(subsystems.lift::down, subsystems.lift);
    }

    public Command to(double height) {
        return new InstantCommand(() -> subsystems.lift.to(height), subsystems.lift);
    }

    public Command toIntake(double offset) {
        return to(8 + offset);
    }

    public Command toJunction(Junction junction) {
        return to(junction.height);
    }
}
