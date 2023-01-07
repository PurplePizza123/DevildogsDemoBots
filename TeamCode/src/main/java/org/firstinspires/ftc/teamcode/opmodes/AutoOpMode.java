package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

public class AutoOpMode extends OpMode {
    public void initialize(Alliance alliance, Side side) {
        MenuSubsystem.auto = true;
        MenuSubsystem.alliance = alliance;
        MenuSubsystem.side = side;

        super.initialize();

        schedule(
            commands.auto.execute()
        );
    }
}
