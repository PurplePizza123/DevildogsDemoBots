package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red North")
@SuppressWarnings("unused")
public class RedNorthOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        super.initialize(RED, NORTH);
    }
}
