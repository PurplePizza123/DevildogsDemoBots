package org.firstinspires.ftc.teamcode.commands;

import static androidx.core.math.MathUtils.clamp;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class BackdropCommands {
    public Command changeAddress(int column, int row) {
        return new InstantCommand(
            () -> {
                config.backdropRow = clamp(config.backdropRow + row, 1, 11);
                config.backdropCol = clamp(config.backdropCol + column, 1, config.backdropRow % 2 == 0 ? 7 : 6);
            },
            Subsystems.backdrop
        );
    }
}
