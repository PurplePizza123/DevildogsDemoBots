package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AutonomousCommands extends Commands {
    public Command blueSouth() {
        // First drive forward about one tile
        // Second strafe left about half a tile
        // Third drive forward a quarter tile
        // Fourth deliver to medium junction
        // Fifth backup a quarter tile
        return drive.move( 0, 1, 0, 24).andThen(
            drive.move( -1, 0, 0, 12),
            drive.move( 0, 1, 0, 6),
            lift.to(LiftSubsystem.LiftPosition.MID),
            intake.setCone()
        );
    }
}
