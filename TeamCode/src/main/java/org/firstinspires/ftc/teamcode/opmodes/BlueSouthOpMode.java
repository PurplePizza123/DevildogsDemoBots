package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Side.LEFT;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@Autonomous(name = "Blue South")
@SuppressWarnings("unused")
public class BlueSouthOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        MenuSubsystem.alliance = BLUE;
        MenuSubsystem.side = SOUTH;
        super.initialize();
    }
}