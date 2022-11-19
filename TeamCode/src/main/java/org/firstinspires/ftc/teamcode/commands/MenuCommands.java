package org.firstinspires.ftc.teamcode.commands;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class MenuCommands extends Commands {
    public Command setPlan(AutonomousCommands.Plan plan){
        return new InstantCommand(() -> subsystems.menu.plan = plan, subsystems.menu);
    }

    public Command changeDelay(double value) {
        return new InstantCommand(() -> subsystems.menu.delay = clamp(subsystems.menu.delay + value, 0, 30), subsystems.menu);
    }

    public Command changeStacks(int value) {
        return new InstantCommand(() -> subsystems.menu.stacks = clamp(subsystems.menu.stacks + value, 1, 2), subsystems.menu);
    }
}
