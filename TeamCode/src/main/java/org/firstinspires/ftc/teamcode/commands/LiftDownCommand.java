package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftDownCommand extends CommandBase {
    private final LiftSubsystem liftSubsystem;

    public LiftDownCommand(LiftSubsystem liftSubsystem) {
        this.liftSubsystem = liftSubsystem;
        addRequirements(liftSubsystem);
    }

    public void initialized() {liftSubsystem.down();}

    @Override
    public boolean isFinished() {
        return true;
    }
}
