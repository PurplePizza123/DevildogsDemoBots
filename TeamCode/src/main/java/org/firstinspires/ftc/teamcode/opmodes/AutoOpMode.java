package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.Commands.auto;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Parking.INNER;

import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class AutoOpMode extends OpMode {
    public void initialize(Alliance alliance, Side side) {
        super.initialize();

        config.alliance = alliance;
        config.side = side;
        config.parking = INNER;
        config.offsetX = 0;
        config.offsetY = 0;
        config.delay = 0;

        Subsystems.drive.setPose(
            Subsystems.nav.getStartPose()
        );

        Controls.initializeAuto();

        waitForStart();

        schedule(
            auto.execute()
        );
    }
}
