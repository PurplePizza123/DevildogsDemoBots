package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import java.util.function.BooleanSupplier;

public class WaitCommands {
    public Command seconds(double value) {
        return milliseconds((long)(value * 1000));
    }

    public Command milliseconds(long value) {
        return new WaitCommand(value);
    }

    public Command until(BooleanSupplier condition) {
        return new WaitUntilCommand(condition);
    }
}
