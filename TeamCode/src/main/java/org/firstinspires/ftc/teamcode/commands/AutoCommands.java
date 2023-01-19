package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;
import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.alliance;
import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.side;
import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.stacks;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class AutoCommands extends Commands {
    public Command execute() {
        return lift.calibrate().andThen(
            vision.detect(),
            auto.scoreStartCone(),
            auto.scoreStack(stacks),
            auto.park()
        );
    }

    public Command scoreStartCone() {
        String column = String.valueOf((char)('X' - alliance.sign * 2));
        int row = 3 + side.sign;
        return drive.toJunction(column + row).andThen(
            intake.setCone()
        );
    }

    public Command scoreStack(int times) {
        String column = String.valueOf((char)('X' - alliance.sign));
        int row = 3 + side.sign * 2;

        SequentialCommandGroup group = new SequentialCommandGroup();

        int stacks = 5;

        group.addCommands(
            drive.toStack(alliance, side),
            intake.getCone(--stacks)
        );

        while (--times >= 0) {
            if (times == 0) {
                group.addCommands(
                    drive.toJunctionAuto(column + (row - side.sign)),
                    intake.setCone()
                );
            } else {
                group.addCommands(
                    drive.toJunctionAuto(column + row),
                    intake.setCone()
                );
            }

            if (times > 0) {
                group.addCommands(
                    drive.toStackAuto(alliance, side),
                    intake.getCone(--stacks)
                );
            }
        }

        return group;
    }

    public Command park() {
        return new SelectCommand(
            () -> {
                String column = alliance == BLUE ? "C" : "D";
                int start = side == SOUTH ? 1 : 4;
                if (alliance == BLUE) start += 2;
                int detectionId = subsystems.vision.getDetectionId();
                int row = start - alliance.sign * detectionId;
                return drive.toTile(column + row).alongWith(
                    lift.to(0)
                );
            }
        );
    }
}
