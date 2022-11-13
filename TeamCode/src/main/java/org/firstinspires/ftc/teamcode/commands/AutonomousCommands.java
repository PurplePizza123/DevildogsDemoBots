package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.INTAKE;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.MID;

import com.arcrobotics.ftclib.command.Command;

public class AutonomousCommands extends Commands {
    public Command blueNorth() {
        return vision.detect().andThen(
            intake.in(),
            lift.to(INTAKE),
            wait.seconds(0.5),
            drive.move(0, 1, 26),
            drive.move(1, 0, 14),
            lift.to(MID),
            drive.move(0, .25, 8),
            intake.setCone(),
            drive.parkBlueNorth()
        );
    }

    public Command blueSouth() {
        return intake.in().andThen(
            lift.to(INTAKE),
            wait.seconds(0.5),
            drive.move(0, 1, 26),
            drive.move(-1, 0, 14),
            lift.to(MID),
            drive.move(0, .25, 8),
            intake.setCone(),
            drive.parkBlueSouth()
        );
    }

    public Command redNorth() {
        return vision.detect().andThen(
            intake.in(),
            lift.to(INTAKE),
            wait.seconds(0.5),
            drive.move(0, 1, 26),
            drive.move(-1, 0, 14),
            lift.to(MID),
            drive.move(0, .25, 8),
            intake.setCone(),
            drive.parkRedNorth()
        );
    }

    public Command redSouth() {
        return intake.in().andThen(
            lift.to(INTAKE),
            wait.seconds(0.5),
            drive.move(0, 1, 26),
            drive.move(1, 0, 14),
            lift.to(MID),
            drive.move(0, .25, 8),
            intake.setCone(),
            drive.parkRedSouth()
        );
    }
}
