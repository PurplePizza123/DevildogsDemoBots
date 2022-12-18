package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

@SuppressWarnings("unused")
public class DriveCommands extends Commands {
    public Command setDrivePower(DriveSubsystem.DrivePower drivePower) {
        return new InstantCommand(() -> subsystems.drive.setDrivePower(drivePower), subsystems.drive);
    }

    public Command input(DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        return new RunCommand(
            () -> subsystems.drive.inputs(
                strafe.getAsDouble(),
                forward.getAsDouble(),
                turn.getAsDouble()
            ), subsystems.drive
        );
    }

    public Command move(double strafe, double forward, double distance) {
        return complete(() -> subsystems.drive.move(strafe, forward, distance));
    }

    public Command turn(double power, double heading) {
        return complete(() -> subsystems.drive.turn(power, heading));
    }

    private Command complete(Runnable runnable) {
        return new InstantCommand(runnable, subsystems.drive).andThen(
            wait.until(() -> subsystems.drive.isBusy())
        );
    }
}
