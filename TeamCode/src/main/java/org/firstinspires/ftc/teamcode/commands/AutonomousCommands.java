package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.HIGH;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.MID;

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
            drive.move(0, 1,side.adapt(0), 52),
            drive.turn(.5,side.adapt(-90)),
            drive.move(0,1,side.adapt(-90),34),
            drive.turn(.5,side.adapt(-180)).alongWith(lift.to(HIGH)),
            drive.move(0, .25,side.adapt(-180), 7),    //TODO change back to 8 if it does not work
            intake.setCone()
        );
    }

    public Command scoreStack(Side side, int times) {
        int stackedCones = 5;

        SequentialCommandGroup group = new SequentialCommandGroup();

        while (--times >= 0) {
            group.addCommands(
                drive.turn(.5, side.adapt(90)).andThen(
                    drive.move(0, 1,side.adapt(90), 63),
                    intake.getCone(--stackedCones),
                    drive.move(0, -1,side.adapt(90), 62),
                    drive.turn(.5, side.adapt(180)).alongWith(lift.to(HIGH)),
                    drive.move(0,1,side.adapt(180),7),  //TODO change back to 8 if it does not work
                    intake.setCone()
                )
            );
        }

        return group;
    }

    public Command park(Side side) {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(1, drive.move(side.adapt(1), 0, 60));
                put(2, drive.move(side.adapt(1), 0, 36));
                put(3, drive.move(side.adapt(1), 0, 0, 12));
            }}, () -> subsystems.vision.getDetectionId() == 0 ? 3 : subsystems.vision.getDetectionId()
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
