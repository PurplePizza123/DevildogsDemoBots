package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeOutCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeOutCommand(IntakeSubsystem subsystem) {
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
    }

    public void initialized() {
        intakeSubsystem.out();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
