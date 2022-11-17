package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommands extends Commands {
    public Command setDrivePower(DriveSubsystem.DrivePower drivePower){
        return new InstantCommand(() -> subsystems.drive.setDrivePower(drivePower), subsystems.menu);
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
        return new FunctionalCommand(
            () -> subsystems.drive.resetEncoders(),
            () -> subsystems.drive.move(strafe, forward, distance),
            i  -> subsystems.drive.stop(),
            () -> subsystems.drive.getDistance() >= distance,
            subsystems.drive
        );
    }

    public Command move(double strafe, double forward, double heading, double distance) {
        return new FunctionalCommand(
            () -> subsystems.drive.resetEncoders(),
            () -> subsystems.drive.move(strafe, forward, heading, distance),
            i  -> subsystems.drive.stop(),
            () -> subsystems.drive.getDistance() >= distance,
            subsystems.drive
        );
    }

    public Command turn(double power, double heading) {
        return new FunctionalCommand(
            () -> subsystems.drive.resetEncoders(),
            () -> subsystems.drive.turn(power, heading),
            i  -> subsystems.drive.stop(),
            () -> subsystems.drive.getRemainderLeftToTurn(heading) > -0.33 &&
                  subsystems.drive.getRemainderLeftToTurn(heading) < +0.33,
            subsystems.drive
        );
    }
}
