package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import org.firstinspires.ftc.teamcode.controllers.MenuController;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

public class AutoOpMode extends OpMode {
    public void initialize(Alliance alliance, Side side) {
        super.initialize();

        config.alliance = alliance;
        config.side = side;

        new MenuController(this);

        waitForStart();

        schedule(
            commands.auto.execute()
        );
    }
}
