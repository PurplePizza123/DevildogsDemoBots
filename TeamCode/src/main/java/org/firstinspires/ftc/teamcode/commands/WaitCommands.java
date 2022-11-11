package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.PurePursuitCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.purepursuit.Waypoint;

import java.util.function.DoubleSupplier;

public class WaitCommands extends Commands {
    public Command seconds(double value) {
        return milliseconds((long) (value * 1000));
    }

    public Command milliseconds(long value) {
        return new WaitCommand(value);
    }
}
