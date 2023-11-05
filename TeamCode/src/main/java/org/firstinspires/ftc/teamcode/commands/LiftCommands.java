package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class LiftCommands extends Commands {
    public Command calibrate() {
        return new InstantCommand(subsystems.lift::calibrate, subsystems.lift).andThen(
            wait.until(() -> subsystems.lift.calibrated).withTimeout(3000)
        );
    }

    public Command change(double offset) {
        return new InstantCommand(() -> subsystems.lift.change(offset), subsystems.lift);
    }

    public Command to(double height) {
        return new InstantCommand(() -> subsystems.lift.to(height), subsystems.lift);
    }
}
