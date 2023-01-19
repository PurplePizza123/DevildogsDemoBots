package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue North")
@SuppressWarnings("unused")
public class BlueNorthOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        super.initialize(BLUE, NORTH);
    }
}
