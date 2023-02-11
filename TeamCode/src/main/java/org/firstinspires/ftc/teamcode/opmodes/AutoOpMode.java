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
        config.stacks = 1;
        config.delay = 0;
        config.navSamples = 50;

        subsystems.drive.setPose(
            subsystems.nav.getStartPose(alliance, side)
        );

        subsystems.rand.enable();

        new MenuController(this);

        waitForStart();

        schedule(
            commands.auto.execute()
        );
    }
}
