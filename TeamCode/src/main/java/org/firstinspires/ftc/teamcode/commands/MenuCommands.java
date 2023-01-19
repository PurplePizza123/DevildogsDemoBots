package org.firstinspires.ftc.teamcode.commands;

import static androidx.core.math.MathUtils.clamp;

import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.delay;
import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.junction;
import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.stacks;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class MenuCommands extends Commands {
    public Command changeDelay(double value) {
        return new InstantCommand(
            () -> delay = clamp(delay + value, 0, 30),
            subsystems.menu
        );
    }

    public Command changeStacks(int value) {
        return new InstantCommand(
            () -> stacks = clamp(stacks + value, 0, 5),
            subsystems.menu
        );
    }

    public Command changeJunction(int column, int row) {
        return new InstantCommand(
            () -> junction = "" +
                (char)clamp(junction.charAt(0) + column, 'V', 'Z') +
                (char)clamp(junction.charAt(1) + row, '1', '5'),
            subsystems.menu
        );
    }
}
