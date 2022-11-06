package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeInCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeInCommand(IntakeSubsystem subsystem) {
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.in();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
