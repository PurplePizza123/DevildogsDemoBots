package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.DriveControl;

@TeleOp(name = "Teleop")
@SuppressWarnings("unused")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        config.junction = "X3";
        config.navSamples = 20;

        waitForStart();

        new DriveControl();
//        new LiftController();
//        new IntakeController();

//        schedule(
//            commands.lift.calibrate()
//        );
    }
}
