package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.WaitCommand;

public class WaitCommands extends Commands {
    public Command seconds(double value) {
        return milliseconds((long) (value * 1000));
    }

    public Command milliseconds(long value) {
        return new WaitCommand(value);
    }
}
