package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

public class DroneCommands extends Commands {
    public Command release() {
        return new InstantCommand(subsystems.drone::release, subsystems.drone);
    }
}
