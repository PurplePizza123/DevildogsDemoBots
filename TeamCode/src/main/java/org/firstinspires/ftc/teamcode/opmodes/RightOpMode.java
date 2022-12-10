package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.AutonomousCommands.Side.RIGHT;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@Autonomous(name = "Right")
public class RightOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        MenuSubsystem.side = RIGHT;
        super.initialize();
    }
}
