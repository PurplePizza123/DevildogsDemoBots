package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.INTAKE;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.MID;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;

import java.util.HashMap;

public class AutonomousCommands extends Commands {
    public Command execute(Side side) {
        return vision.detect().andThen(
            autonomous.scoreFirstCone(side),
            autonomous.park(side)
        );
    }

    public Command scoreFirstCone(Side side) {
        return intake.getCone().andThen(
            drive.move(0, 1, 26),
            drive.move(side.adapt(1), 0, 14),
            lift.to(MID),
            drive.move(0, .25, 8),
            intake.setCone()
        );
    }

    public Command park(Side side) {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(0, drive.move(side.adapt(-1), 0, 14));
                put(1, drive.move(side.adapt(-1), 0, 42));
                put(2, drive.move(side.adapt(-1), 0, 14));
                put(3, drive.move(side.adapt(1), 0, 0, 14));
            }}, () -> subsystems.vision.getDetectionId()
        );
    }

    public enum Side{
        LEFT(1), RIGHT(-1);

        private int sign;

        Side(int sign) {
            this.sign = sign;
        }

        public double adapt(double value){
            return this.sign * value;
        }
    }
}
