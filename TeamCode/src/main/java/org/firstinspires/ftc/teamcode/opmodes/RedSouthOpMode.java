package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red South")
@SuppressWarnings("unused")
public class RedSouthOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        super.initialize(RED, SOUTH);
    }
}
