package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.GROUND;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.HIGH;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import java.util.HashMap;

public class AutonomousCommands extends Commands {
    public Command execute(Side side) {
        return vision.detect().andThen(
            autonomous.scoreStartCone(side),
            autonomous.scoreStack(side, 1),
            autonomous.park(side)
        );
    }

    public Command scoreStartCone(Side side) {
        return intake.getCone().andThen(
            drive.move(0, 1, 4),
            drive.move(1, 0, 24),
            drive.move(0, 1, 24).alongWith(lift.to(HIGH)),
            drive.turn(1, side.adapt(-45)),
            drive.move(0, 1, 7),
            intake.setCone()
        );
    }

    public Command scoreStack(Side side, int times) {
        int stackedCones = 5;

        SequentialCommandGroup group = new SequentialCommandGroup(
            drive.turn(1, 0),
            drive.move(0, 1, 24),
            drive.turn(1, side.adapt(90))
        );

        while (--times >= 0) {
            group.addCommands(
                drive.move(0, 1, 48),
                intake.getCone(--stackedCones),
                drive.move(0, -1, 48).alongWith(lift.to(HIGH)),
                drive.turn(1, side.adapt(-135)),
                drive.move(0, 1, 7),
                intake.setCone(stackedCones),
                drive.turn(1, side.adapt(90))
            );
        }

        return group;
    }

    public Command park(Side side) {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(1, drive.move(0, 1, side == Side.LEFT ? 48 : 0));
                put(2, drive.move(0, 1, 24));
                put(3, drive.move(0, 1, side == Side.LEFT ? 0 : 48));
            }}, () -> subsystems.vision.getDetectionId() == 0 ? (side == Side.LEFT ? 3 : 1) : subsystems.vision.getDetectionId()
        ).andThen(lift.to(GROUND));
    }

    public enum Side {
        LEFT(1), RIGHT(-1);

        private int sign;

        Side(int sign) {
            this.sign = sign;
        }

        public double adapt(double value) {
            return this.sign * value;
        }
    }

    public enum Plan {
        A, B
    }
}
