package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.conveyor;
import static org.firstinspires.ftc.teamcode.commands.Commands.sweeper;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;

public class IntakeCommands {
    public Command getPixels() {
        return sweeper.in().andThen(
            conveyor.in(),
            wait.seconds(0), // TODO: wait for signal
            sweeper.out(),
            wait.seconds(1),
            conveyor.stop(),
            sweeper.stop()
        );
    }

    public Command in() {
        return conveyor.in();
    }

    public Command in(double power) {
        return conveyor.in(power);
    }

    public Command out() {
        return conveyor.out();
    }

    public Command out(double power) {
        return conveyor.out(power);
    }

    public Command stop() {
        return conveyor.stop();
    }
}
