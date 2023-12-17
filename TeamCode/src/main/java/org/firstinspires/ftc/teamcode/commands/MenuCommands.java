package org.firstinspires.ftc.teamcode.commands;

import static androidx.core.math.MathUtils.clamp;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Parking.INNER;
import static org.firstinspires.ftc.teamcode.game.Parking.OUTER;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.game.Backdrop;
import org.firstinspires.ftc.teamcode.game.Parking;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class MenuCommands {
    public Command toggleAlliance() {
        return new InstantCommand(
            () -> config.alliance = config.alliance == null || config.alliance == BLUE ? RED : BLUE,
            Subsystems.menu
        );
    }

    public Command toggleSide() {
        return new InstantCommand(
            () -> config.side = config.side == null || config.side == SOUTH ? NORTH : SOUTH,
            Subsystems.menu
        );
    }
    public Command toggleParking() {
        return new InstantCommand(
            () -> (config.parking) = config.parking == null || config.parking == OUTER ? INNER : OUTER,
            Subsystems.menu
        );
    }

    public Command toggleBackdrop() {
        return new InstantCommand(
                () -> (config.backdrop) = config.backdrop == null || config.backdrop == Backdrop.LEFT ? Backdrop.RIGHT : Backdrop.LEFT,
                Subsystems.menu
        );
    }


    public Command setStartPose() {
        return new InstantCommand(
            () -> Subsystems.drive.setPose(
                Subsystems.nav.getStartPose(config.alliance, config.side)
            ), Subsystems.drive, Subsystems.nav
        );
    }

    public Command changeOffsetX(double value) {
        return new InstantCommand(
            () -> config.offsetX = clamp(config.offsetX + value, -12, 12),
            Subsystems.menu
        );
    }

    public Command changeOffsetY(double value) {
        return new InstantCommand(
            () -> config.offsetY = clamp(config.offsetY + value, -12, 12),
            Subsystems.menu
        );
    }

    public Command changeDelay(double value) {
        return new InstantCommand(
            () -> config.delay = clamp(config.delay + value, 0, 30),
            Subsystems.menu
        );
    }
}
