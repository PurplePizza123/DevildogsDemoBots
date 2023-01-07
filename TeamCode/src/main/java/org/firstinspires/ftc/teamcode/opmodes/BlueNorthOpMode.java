package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Side.LEFT;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@Autonomous(name = "Blue North")
@SuppressWarnings("unused")
public class BlueNorthOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        MenuSubsystem.alliance = BLUE;
        MenuSubsystem.side = NORTH;
        super.initialize();
    }
}
