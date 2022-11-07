package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.PurePursuitCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.purepursuit.Waypoint;

import java.util.function.DoubleSupplier;

public class DriveCommands extends Commands {
    public Command input(DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        return new RunCommand(
            () -> subsystems.drive.move(
                strafe.getAsDouble(),
                forward.getAsDouble(),
                turn.getAsDouble()
            ), subsystems.drive
        );
    }

    public PurePursuitCommand to(Waypoint... waypoints) {
        return new PurePursuitCommand(
            subsystems.drive.mecanum,
            subsystems.drive.odometry,
            waypoints
        );
    }
}
