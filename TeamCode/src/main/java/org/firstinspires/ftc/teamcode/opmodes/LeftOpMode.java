package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.AutonomousCommands.Side.LEFT;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@Autonomous(name = "Left")
public class LeftOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        MenuSubsystem.side = LEFT;
        super.initialize();
    }
}
