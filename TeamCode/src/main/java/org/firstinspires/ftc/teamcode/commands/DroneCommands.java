package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class DroneCommands {
    public Command release() {
        return new InstantCommand(Subsystems.drone::release, Subsystems.drone);
    }
}
