package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue South")
@SuppressWarnings("unused")
public class BlueSouthOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        super.initialize(BLUE, SOUTH);
    }
}
