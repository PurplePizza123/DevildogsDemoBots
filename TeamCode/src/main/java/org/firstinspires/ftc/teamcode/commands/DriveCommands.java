package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.nav;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.vision;

import com.acmerobotics.roadrunner.Pose2d;
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

    public Command temp() {
        return drive.follow(
            t -> t.strafeTo(
                new Pose2d(23.5 * 2, 23.5 * -3 + 6, Math.toRadians(90))
            )
        ).andThen(
            wait.seconds(3),
            drive.follow(
                t -> t.strafeTo(
                    new Pose2d(23.5 * 2, 0, Math.toRadians(90))
                )
            )
        ).andThen(
            wait.seconds(3),
            drive.follow(
                t -> t.strafeTo(
                    new Pose2d(0, 0, Math.toRadians(90))
                )
            )
        );
    }

    public Command toSpikeMark() {
        return drive.follow(
            t -> t.strafeToLinearHeading(
                nav.getSpikeMarkPose(vision.recognitionId)
            )
        );
    }

    public Command toSpikeMarkTile() {
        return drive.follow(
            t -> t.strafeToLinearHeading(
                nav.getSpikeMarkTilePose()
            )
        );
    }

    public Command toBackdropApproach1() {
        return drive.follow(
            t -> t.strafeToLinearHeading(
                nav.getBackdropApproachPose1()
            )
        );
    }

    public Command toBackdropApproach2() {
        return drive.follow(
            t -> t.turnTo(Math.toRadians(90))
        );
    }

    public Command toBackdrop() {
        return drive.follow(
            t -> t.strafeToLinearHeading(
                nav.getBackdropPose(vision.recognitionId)
            )
        );
    }

    public Command toParking() {
        return drive.follow(
            t -> t.strafeToLinearHeading(
                nav.getParkingPose()
            )
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
