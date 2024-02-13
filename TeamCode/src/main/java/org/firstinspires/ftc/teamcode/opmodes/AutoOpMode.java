package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.Commands.auto;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.game.Backdrop.LEFT;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Parking.INNER;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;
@Autonomous(name = "Auto")
@SuppressWarnings("unused")
public class AutoOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        config.alliance = Alliance.UNKNOWN;
        config.side = Side.UNKNOWN;
        config.parking = INNER;
        config.backdrop = LEFT;
        config.backdropRow = 1;
        config.backdropCol = 1;
        config.offsetX = 0;
        config.offsetY = 0;
        config.delay = 0;

        Controls.initializeAuto();

        Subsystems.vision.setActiveCamera(hardware.frontWebcam);

        waitForStart();

        if (config.alliance == Alliance.UNKNOWN || config.side == Side.UNKNOWN)
            throw new RuntimeException("Alliance and/or Side is Unknown");

        schedule(
            auto.execute()
        );
    }
}
