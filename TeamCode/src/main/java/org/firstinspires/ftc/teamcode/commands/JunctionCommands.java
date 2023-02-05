package org.firstinspires.ftc.teamcode.commands;

import static androidx.core.math.MathUtils.clamp;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class JunctionCommands extends Commands {
    public Command changeJunction(int column, int row) {
        return new InstantCommand(
            () -> config.junction = "" +
                (char)clamp(config.junction.charAt(0) + column, 'V', 'Z') +
                (char)clamp(config.junction.charAt(1) + row, '1', '5'),
            subsystems.junction
        );
    }
}
