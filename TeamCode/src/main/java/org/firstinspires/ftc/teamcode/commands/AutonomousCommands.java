package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.INTAKE;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.MID;

import com.arcrobotics.ftclib.command.Command;

public class AutonomousCommands extends Commands {
    public Command blueSouth() {
        return intake.in().andThen(
            lift.to(INTAKE),
            wait.seconds(0.5),
            drive.move(0, 1, 26),
            drive.move(-1, 0, 14),
            lift.to(MID),
            drive.move(0, .25, 8),
            intake.setCone(),
            drive.move(1, 0, 14)
        );
    }

    public Command blueNorth() {
        return intake.in().andThen(
            lift.to(INTAKE),
            wait.seconds(0.5),
            drive.move(0, 1, 26),
            drive.move(1, 0, 14),
            lift.to(MID),
            drive.move(0, .25, 8),
            intake.setCone(),
            drive.move(-1, 0, 14)
        );
    }
}
