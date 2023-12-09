package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.Commands.vision;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.DriveControl;
import org.firstinspires.ftc.teamcode.controls.DroneControl;
import org.firstinspires.ftc.teamcode.controls.HoistControl;
import org.firstinspires.ftc.teamcode.controls.DepositControl;

@TeleOp(name = "Teleop")
@SuppressWarnings("unused")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        config.navSamples = 20;

        waitForStart();

        schedule(
            vision.detect()
        );

        new DriveControl();
        /*new IntakeControl();*/
        /*new LiftControl();*/
        new DepositControl();
        new HoistControl();
        new DroneControl();
    }
}
