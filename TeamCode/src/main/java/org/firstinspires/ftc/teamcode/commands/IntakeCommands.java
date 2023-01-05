package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.GROUND;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.INTAKE;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

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
            lift.to(GROUND, offset),
            wait.seconds(0.5),
            lift.to(INTAKE, offset),
            intake.stop(true)
        );
    }

    public Command setCone() {
        return setCone(0);
    }

    public Command setCone(double offset) {
        return intake.out().andThen(
            wait.seconds(.3),
            drive.setHeading(),
            intake.stop(false),
            drive.move(0, -1, 8),
            lift.to(INTAKE, offset)
        );
    }
}
