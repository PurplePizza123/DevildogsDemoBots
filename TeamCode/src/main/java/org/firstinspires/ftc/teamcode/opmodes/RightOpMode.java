package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Side.RIGHT;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@Autonomous(name = "Right")
@SuppressWarnings("unused")
public class RightOpMode extends AutoOpMode {
    @Override
    public void initialize() {
        MenuSubsystem.side = RIGHT;
        super.initialize();
    }
}
