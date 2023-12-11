package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class HoistCommands {
    public Command up() {
        return new InstantCommand(Subsystems.hoist::up, Subsystems.hoist);
    }

    public Command stop() {
        return new InstantCommand(Subsystems.hoist::stop, Subsystems.hoist);
    }

    public Command change(double offset) {
        return new InstantCommand(() -> Subsystems.hoist.change(offset), Subsystems.hoist);
    }
}
