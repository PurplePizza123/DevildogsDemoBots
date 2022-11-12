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

    public Command stop() {
        return new InstantCommand(subsystems.intake::stop, subsystems.intake);
    }

    public Command getCone() {
        return intake.in().andThen(
            lift.to(GROUND),
            wait.seconds(.5),
            lift.to(INTAKE),
            intake.stop()
        );
    }

    public Command setCone() {
        return intake.out().andThen(
            wait.seconds(1),
            drive.move(0, -1, 0, 6),
            lift.to(INTAKE)
        );
    }
}
