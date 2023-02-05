package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class AutoCommands extends Commands {
    public Command execute() {
        return lift.calibrate().andThen(
            rand.detect(),
            auto.scoreStartCone(),
            auto.scoreStack(config.stacks),
            auto.park()
        );
    }

    public Command scoreStartCone() {
        String column = String.valueOf((char)('X' - config.alliance.sign * 2));
        int row = 3 + config.side.sign;
        return drive.toJunction(column + row).andThen(
            intake.setCone()
        );
    }

    public Command scoreStack(int times) {
        String column = String.valueOf((char)('X' - config.alliance.sign));
        int row = 3 + config.side.sign * 2;

        SequentialCommandGroup group = new SequentialCommandGroup();

        int stacks = 5;

        group.addCommands(
            drive.toStack(config.alliance, config.side),
            intake.getCone(--stacks)
        );
//&& config.timer.seconds() <
        while (--times >= 0 ) {
            if (times == 0) {
                group.addCommands(
                    drive.toJunctionAuto(column + (row - config.side.sign)),
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
                    drive.toStackAuto(config.alliance, config.side),
                    intake.getCone(--stacks)
                );
            }
        }

        return group;
    }

    public Command park() {
        return new SelectCommand(
            () -> {
                String column = config.alliance == BLUE ? "C" : "D";
                int start = config.side == SOUTH ? 1 : 4;
                if (config.alliance == BLUE) start += 2;
                int detectionId = subsystems.rand.getDetectionId();
                int row = start - config.alliance.sign * detectionId;
                return drive.toTile(column + row).alongWith(
                    lift.to(0)
                );
            }
        );
    }
}
