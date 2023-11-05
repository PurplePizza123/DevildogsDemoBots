package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class LiftCommands {
    public Command calibrate() {
        return new InstantCommand(Subsystems.lift::calibrate, Subsystems.lift).andThen(
            wait.until(() -> Subsystems.lift.calibrated).withTimeout(3000)
        );
    }

    public Command change(double offset) {
        return new InstantCommand(() -> Subsystems.lift.change(offset), Subsystems.lift);
    }

    public Command to(double height) {
        return new InstantCommand(() -> Subsystems.lift.to(height), Subsystems.lift);
    }
}
