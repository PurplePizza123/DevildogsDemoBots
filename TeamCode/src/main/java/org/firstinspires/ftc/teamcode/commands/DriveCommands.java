package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

import java.util.function.DoubleSupplier;

@SuppressWarnings({"unused"})
public class DriveCommands {
    public Command setDrivePower(double power) {
        return new InstantCommand(() -> Subsystems.drive.power = power, Subsystems.drive);
    }

    public Command input(DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn) {
        return new RunCommand(
            () -> Subsystems.drive.inputs(
                forward.getAsDouble(),
                strafe.getAsDouble(),
                turn.getAsDouble()
            ), Subsystems.drive
        );
    }
}
