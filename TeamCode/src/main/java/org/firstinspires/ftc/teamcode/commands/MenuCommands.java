package org.firstinspires.ftc.teamcode.commands;

import static androidx.core.math.MathUtils.clamp;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class MenuCommands extends Commands {
    public Command toggleAlliance() {
        return new InstantCommand(
            () -> config.alliance = config.alliance == null || config.alliance == BLUE ? RED : BLUE,
            subsystems.menu
        );
    }

    public Command toggleSide() {
        return new InstantCommand(
            () -> config.side = config.side == null || config.side == SOUTH ? NORTH : SOUTH,
            subsystems.menu
        );
    }

    public Command setStartPose() {
        return new InstantCommand(
            () -> subsystems.drive.setPose(
                subsystems.nav.getStartPose(config.alliance, config.side)
            ), subsystems.drive, subsystems.nav
        );
    }

    public Command changeOffsetX(double value) {
        return new InstantCommand(
            () -> config.offsetX = clamp(config.offsetX + value, -12, 12),
            subsystems.menu
        );
    }

    public Command changeOffsetY(double value) {
        return new InstantCommand(
            () -> config.offsetY = clamp(config.offsetY + value, -12, 12),
            subsystems.menu
        );
    }

    public Command changeDelay(double value) {
        return new InstantCommand(
            () -> config.delay = clamp(config.delay + value, 0, 30),
            subsystems.menu
        );
    }

    public Command changeStacks(int value) {
        return new InstantCommand(
            () -> config.stacks = clamp(config.stacks + value, 0, 5),
            subsystems.menu
        );
    }
}
