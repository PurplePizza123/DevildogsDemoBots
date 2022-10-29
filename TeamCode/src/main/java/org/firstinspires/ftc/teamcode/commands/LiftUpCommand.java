package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftUpCommand extends CommandBase {
    private final LiftSubsystem liftSubsystem;

    public LiftUpCommand(LiftSubsystem liftSubsystem) {
        this.liftSubsystem = liftSubsystem;
        addRequirements(liftSubsystem);
    }

    public void initialized() {
        liftSubsystem.up();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
