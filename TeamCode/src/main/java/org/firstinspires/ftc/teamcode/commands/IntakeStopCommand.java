package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeStopCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeStopCommand(IntakeSubsystem subsystem) {
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
    }

    public void initialized() {
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
