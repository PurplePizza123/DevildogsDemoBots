package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class HoistCommands {
    public Command change(double offset) {
        return new InstantCommand(() -> Subsystems.hoist.change(offset), Subsystems.hoist);
    }
}
