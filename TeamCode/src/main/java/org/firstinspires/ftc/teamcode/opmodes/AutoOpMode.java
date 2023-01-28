package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

public class AutoOpMode extends OpMode {
    public void initialize(Alliance alliance, Side side) {
        config.auto = true;
        config.alliance = alliance;
        config.side = side;

        super.initialize();

        schedule(
            commands.auto.execute()
        );
    }
}
