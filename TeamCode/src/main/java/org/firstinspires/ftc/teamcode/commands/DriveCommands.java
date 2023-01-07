package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
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

    public Command toPose(Pose2d pose) {
        return drive.complete(
                () -> subsystems.drive.to(
                 subsystems.nav.getTransitionPoses(
                     subsystems.drive.getPose(),
                     pose, 0, true
                 )
            )
        );
    }

    public Command toTile(String label) {
        return toPose(
            subsystems.nav.getTilePose(label)
        );
    }

    public Command toJunction(String label) {
        return toPose(
            subsystems.nav.getJunctionPose(label)
        );
    }

    public Command toStack(Alliance alliance, Side side) {
        return toPose(
            subsystems.nav.getStackPose(alliance, side)
        );
    }

    public Command toSubstation(Alliance alliance, Side side) {
        return toPose(
           subsystems.nav.getSubstationPose(alliance, side)
        );
    }

    public Command toTerminal(Alliance alliance, Side side) {
        return toPose(
            subsystems.nav.getTerminalPose(alliance, side)
        );
    }

    private Command complete(Runnable runnable) {
        return new InstantCommand(runnable, subsystems.drive).andThen(
            wait.until(() -> !subsystems.drive.isBusy())
        );
    }
}
