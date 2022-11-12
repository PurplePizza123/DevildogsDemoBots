package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.PurePursuitCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.purepursuit.Waypoint;

import java.util.function.DoubleSupplier;

public class DriveCommands extends Commands {
    public Command input(DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        return new RunCommand(
            () -> subsystems.drive.inputs(
                strafe.getAsDouble(),
                forward.getAsDouble(),
                turn.getAsDouble()
            ), subsystems.drive
        );
    }

    public Command move(double strafe, double forward, double turn, double distance){
        return new FunctionalCommand(
            () -> subsystems.drive.resetEncoders(),
            () -> subsystems.drive.move(strafe, forward, turn, distance),
            i -> subsystems.drive.stop(),
            () -> subsystems.drive.getDistance() >= distance,
            subsystems.drive
        );
    }

    public Command turn(double power, double heading){
        return new FunctionalCommand(
                () -> subsystems.drive.resetEncoders(),
                () -> subsystems.drive.turn(power, heading),
                i -> subsystems.drive.stop(),
                () -> subsystems.drive.getRemainderLeftToTurn(heading) > -1 ||
                    subsystems.drive.getRemainderLeftToTurn(heading) < 1,
                subsystems.drive
        );
    }


    public PurePursuitCommand move(Waypoint... waypoints) {
        return new PurePursuitCommand(
            subsystems.drive.mecanum,
            subsystems.drive.odometry,
            waypoints
        );
    }
}
