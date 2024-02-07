package org.firstinspires.ftc.teamcode.commands;

import static androidx.core.math.MathUtils.clamp;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class BackdropCommands {
    public Command changeAddress(int column, int row) {
        return new InstantCommand(
                () -> config.backdrop = "" +
                        (char)clamp(config.backdropCol.charAt(0) + column, 'V', 'Z') +
                        (char)clamp(config.backdropRow.charAt(1) + row, '1', '5'),
                Subsystems.backdrop
        );
    }
}
