package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Side.LEFT;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@Autonomous(name = "Red South")
@SuppressWarnings("unused")
public class RedSouthOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        MenuSubsystem.alliance = RED;
        MenuSubsystem.side = SOUTH;
        super.initialize();
    }
}