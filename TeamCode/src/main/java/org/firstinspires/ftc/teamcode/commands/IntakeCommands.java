package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.conveyor;
import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;

public class IntakeCommands {
    public Command getPixels() {
        return conveyor.in().andThen(
            wait.seconds(1),
            conveyor.stop()
        );
    }

    public Command in() {
        return lift.to(2).andThen(
            conveyor.in(),
            deposit.open()
        );
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
        return conveyor.stop().andThen(
            lift.to(0),
            deposit.close()
        );
    }
}
