package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.Commands.auto;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Parking.INNER;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

@Autonomous(name = "Auto")
@SuppressWarnings("unused")
public class AutoOpMode extends OpMode {
    public void initialize() {
        super.initialize();

        config.alliance = Alliance.UNKNOWN;
        config.side = Side.UNKNOWN;
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
