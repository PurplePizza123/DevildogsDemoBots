package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.controllers.MenuController;

@Autonomous(name = "Autonomous")
@SuppressWarnings("unused")
public class AutoOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        config.stacks = 2;
        config.delay = 0;

        new MenuController(this);

        waitForStart();

        schedule(
            commands.auto.execute()
        );
    }
}
