package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class AutoCommands extends Commands {
    public Command execute() {
        return lift.calibrate().andThen(
            rand.detect(),
            auto.park()
        );
    }

    public Command park() {
        return null;
    }
}
