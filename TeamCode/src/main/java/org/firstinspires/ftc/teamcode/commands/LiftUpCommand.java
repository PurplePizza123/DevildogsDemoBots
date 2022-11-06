package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftUpCommand extends CommandBase {
    private final LiftSubsystem liftSubsystem;

    public LiftUpCommand(LiftSubsystem liftSubsystem) {
        this.liftSubsystem = liftSubsystem;
        addRequirements(liftSubsystem);
    }

    @Override
    public void initialize() {
        liftSubsystem.up();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
