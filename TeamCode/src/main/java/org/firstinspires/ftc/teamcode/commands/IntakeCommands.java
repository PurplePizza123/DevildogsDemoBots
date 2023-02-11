package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

public class IntakeCommands extends Commands {
    public Command in() {
        return new InstantCommand(subsystems.intake::in, subsystems.intake);
    }

    public Command out() {
        return new InstantCommand(subsystems.intake::out, subsystems.intake);
    }

    public Command stop(boolean pulse) {
        return new InstantCommand(() -> subsystems.intake.stop(pulse), subsystems.intake);
    }

    public Command getCone() {
        return getCone(0);
    }

    public Command getCone(double offset) {
        return intake.in().andThen(
            lift.to(offset),
            wait.seconds(0.30 + (0.25 / (offset + 1))),
            lift.toIntake(offset),
            intake.stop(true)
        );
    }

    public Command setCone() {
        return setCone(0);
    }

    public Command setCone(double offset) {
        return intake.out().andThen(
            lift.change(-3),
            wait.seconds(0.33),
            lift.change(+3),
            intake.stop(false),
            new ConditionalCommand(
                wait.seconds(0),
                drive.forward(-8),
                () -> config.auto
            ),
            lift.toIntake(offset)
        );
    }
}
