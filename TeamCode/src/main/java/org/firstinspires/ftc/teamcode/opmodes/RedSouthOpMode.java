package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.AutonomousCommands.Side.LEFT;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class RedSouthOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        schedule(
            commands.autonomous.execute(LEFT)
        );
    }
}
