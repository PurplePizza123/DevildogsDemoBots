package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class ArmCommands {
    public Command up() {
        return new InstantCommand(
            Subsystems.arm::up,
            Subsystems.arm
        );
    }

    public Command down() {
        return new InstantCommand(
            Subsystems.arm::down,
            Subsystems.arm
        );
    }
}
