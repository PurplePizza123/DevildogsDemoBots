package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;

public class IntakeCommands extends Commands {
    public Command getPixels() {
        return sweeper.in().andThen(
                conveyor.in(),
                wait.seconds(0), // TODO wait for signal
                sweeper.out(),
                wait.seconds(1), // TODO figure out time it takes for a pixel to get out of conveyor
                conveyor.stop(),
                sweeper.stop()
        );
    }
}
