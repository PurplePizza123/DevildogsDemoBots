package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.PurePursuitCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SelectCommand;

import java.util.HashMap;
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

    public Command move(double strafe, double forward, double distance){
        return new FunctionalCommand(
            () -> subsystems.drive.resetEncoders(),
            () -> subsystems.drive.move(strafe, forward, distance),
            i  -> subsystems.drive.stop(),
            () -> subsystems.drive.getDistance() >= distance,
            subsystems.drive
        );
    }

    public Command move(double strafe, double forward, double turn, double distance){
        return new FunctionalCommand(
            () -> subsystems.drive.resetEncoders(),
            () -> subsystems.drive.move(strafe, forward, turn, distance),
            i  -> subsystems.drive.stop(),
            () -> subsystems.drive.getDistance() >= distance,
            subsystems.drive
        );
    }

    public Command turn(double power, double heading){
        return new FunctionalCommand(
            () -> subsystems.drive.resetEncoders(),
            () -> subsystems.drive.turn(power, heading),
            i  -> subsystems.drive.stop(),
            () -> subsystems.drive.getRemainderLeftToTurn(heading) > -1 ||
                  subsystems.drive.getRemainderLeftToTurn(heading) < 1,
            subsystems.drive
        );
    }

    public Command parkBlueNorth() {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(0, drive.move(-1, 0, 14));
                put(1, drive.move(-1, 0, 42));
                put(2, drive.move(-1, 0, 14));
                put(3, drive.move(1, 0, 0, 14));
            }}, () -> subsystems.vision.getDetectionId()
        );
    }

    public Command parkBlueSouth() {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(0, drive.move(1, 0, 14));
                put(1, drive.move(1, 0, 42));
                put(2, drive.move(1, 0, 14));
                put(3, drive.move(-1, 0, 0, 14));
            }}, () -> subsystems.vision.getDetectionId()
        );
    }

    public Command parkRedNorth() {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(0, drive.move(1, 0, 14));
                put(1, drive.move(1, 0, 42));
                put(2, drive.move(1, 0, 14));
                put(3, drive.move(-1, 0, 0, 14));
            }}, () -> subsystems.vision.getDetectionId()
        );
    }

    public Command parkRedSouth() {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(0, drive.move(-1, 0, 14));
                put(1, drive.move(-1, 0, 42));
                put(2, drive.move(-1, 0, 14));
                put(3, drive.move(1, 0, 0, 14));
            }}, () -> subsystems.vision.getDetectionId()
        );
    }
}
