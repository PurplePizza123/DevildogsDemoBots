package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.adaptations.roadrunner.Trajectory;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

import java.util.function.Consumer;
import java.util.function.DoubleSupplier;

@SuppressWarnings({"unused", "unchecked"})
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

    public Command follow(Consumer<Trajectory> trajectory) {
        return complete(
            () -> Subsystems.drive.followTrajectoryAsync(trajectory)
        );
    }

    private Command complete(Runnable runnable) {
        return new InstantCommand(runnable, Subsystems.drive).andThen(
            wait.until(() -> !Subsystems.drive.isBusy())
        );
    }
}
