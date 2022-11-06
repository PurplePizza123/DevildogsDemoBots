package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftStopCommand extends CommandBase {
    private final LiftSubsystem liftSubsystem;

    public LiftStopCommand(LiftSubsystem liftsubsystem) {
        this.liftSubsystem = liftsubsystem;
        addRequirements(liftsubsystem);
    }

    @Override
    public void initialize() {
        liftSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
