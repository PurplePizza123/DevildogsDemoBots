package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.hacks.Offsets;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "unchecked"})
public class DriveCommands {
    public static final double INTAKE_OFFSET = -4.75;
    public static final double IS_BUSY_OFFSET = -0.25;

    public Command setDrivePower(double power) {
        return new InstantCommand(() -> Subsystems.drive.power = power, Subsystems.drive);
    }

    public Command input(DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        return new RunCommand(
            () -> Subsystems.drive.inputs(
                strafe.getAsDouble(),
                forward.getAsDouble(),
                turn.getAsDouble()
            ), Subsystems.drive
        );
    }

    public Command strafe(double distance) {
        return complete(() -> Subsystems.drive.strafe(distance));
    }

    public Command forward(double distance) {
        return complete(() -> Subsystems.drive.forward(distance));
    }

    public Command turn(double heading) {
        return complete(() -> Subsystems.drive.turn(heading));
    }

    public Command toPose(Pose2d pose, Consumer<Offsets>... consumers) {
        return complete(
            () -> Subsystems.drive.to(
                Subsystems.nav.getTransitionPoses(
                    Subsystems.drive.getPose(),
                    pose, consumers
                )
            )
        );
    }

    public Command toTile(Supplier<String> supplier) {
        return drive.toTile(supplier.get());
    }

    public Command toTile(String label) {
        Pose2d pose = Subsystems.nav.getTilePose(label);
        return drive.toPose(pose);
    }

    public Command toTile(String label, Consumer<Offsets>... consumers) {
        return drive.toPose(
            Subsystems.nav.getTilePose(label),
            consumers
        );
    }

    private Command complete(Runnable runnable) {
        return new InstantCommand(runnable, Subsystems.drive).andThen(
            wait.until(() -> !Subsystems.drive.isBusy(IS_BUSY_OFFSET))
        );
    }
}
