package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class MenuCommands extends Commands {
    public Command setPlan(AutonomousCommands.Plan plan){
        return new InstantCommand(() -> subsystems.menu.plan = plan, subsystems.menu);
    }

    public Command changeDelay(double value) {
        return new InstantCommand(() -> subsystems.menu.delay += value, subsystems.menu);
    }
}
