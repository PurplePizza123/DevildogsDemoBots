package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SelectCommand;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Junction;
import org.firstinspires.ftc.teamcode.game.Side;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DriveCommands extends Commands {
    private static final double INTAKE_OFFSET = -4.75;

    public Command setDrivePower(double power) {
        return new InstantCommand(() -> subsystems.drive.power = power, subsystems.drive);
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

    public Command strafe(double distance) {
        return complete(() -> subsystems.drive.strafe(distance));
    }

    public Command forward(double distance) {
        return complete(() -> subsystems.drive.forward(distance));
    }

    public Command turn(double heading) {
        return complete(() -> subsystems.drive.turn(heading));
    }

    public Command toPose(Pose2d pose, double offset, boolean y1st) {
        return toPose(pose, 0, offset, y1st);
    }

    public Command toPose(Pose2d pose, double stxo, double offset, boolean y1st) {
        return complete(
            () -> subsystems.drive.to(
                subsystems.nav.getTransitionPoses(
                    subsystems.drive.getPose(),
                    pose, stxo, offset, y1st
                )
            )
        );
    }

    public Command toTile(String label) {
        Pose2d pose = subsystems.nav.getTilePose(label);
        return drive.toPose(pose, 0, true);
    }

    public Command toTile(Supplier<String> supplier) {
        return drive.toTile(supplier.get());
    }

    public Command toJunction(String label, double stxo) {
        Pose2d pose = subsystems.nav.getJunctionPose(label);
        return drive.toPose(pose, stxo, INTAKE_OFFSET, true).alongWith(
            lift.toJunction(Junction.get(label))
        ).andThen(
            drive.setDrivePower(0.25)
        );
    }

    public Command toJunction(String label) {
        return toJunction(label, 0);
    }

    public Command toJunctionAuto(String label) {
        return toJunction(label, -0.2);
    }

    public Command toJunction() {
        return new SelectCommand(
            () -> drive.toJunction(config.junction)
        );
    }


    public Command toStackRight() {
        return toStack(config.alliance, config.alliance == RED ? NORTH : SOUTH);
    }

    public Command toStackLeft() {
        return toStack(config.alliance, config.alliance == RED ? SOUTH : NORTH);
    }

    public Command toStack() {
        return toStack(config.alliance, config.side);
    }

    public Command toStack(Alliance alliance, Side side) {
        return toStack(alliance, side, 0);
    }

    public Command toStack(Alliance alliance, Side side, double stxo) {
        return new SelectCommand(
            () -> {
                Pose2d pose = subsystems.nav.getStackPose(alliance, side);
                return drive.toPose(pose, stxo, INTAKE_OFFSET, true).alongWith(
                    lift.toIntake(0)
                );
            }
        );
    }

    public Command toStackAuto() {
        return toStack(config.alliance, config.side, 0.2);
    }

    public Command toSubstation() {
        return new SelectCommand(
            () -> {
                Pose2d pose = subsystems.nav.getSubstationPose(config.alliance, config.side);
                return drive.toPose(pose, INTAKE_OFFSET, true).alongWith(
                    lift.toIntake(0)
                );
            }
        );
    }

    public Command toTerminal(Alliance alliance, Side side) {
        Pose2d pose = subsystems.nav.getTerminalPose(alliance, side);
        return drive.toPose(pose, INTAKE_OFFSET, true).alongWith(
            lift.toIntake(0)
        );
    }

    private Command complete(Runnable runnable) {
        return new InstantCommand(runnable, subsystems.drive).andThen(
            wait.until(() -> !subsystems.drive.isBusy())
        );
    }
}
