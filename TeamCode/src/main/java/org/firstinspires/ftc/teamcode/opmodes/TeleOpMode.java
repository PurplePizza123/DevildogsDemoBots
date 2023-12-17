package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.commands.Commands.drone;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.DriveControl;
import org.firstinspires.ftc.teamcode.controls.DroneControl;
//import org.firstinspires.ftc.teamcode.controls.HoistControl;
import org.firstinspires.ftc.teamcode.controls.HoistControl;
import org.firstinspires.ftc.teamcode.controls.IntakeControl;
import org.firstinspires.ftc.teamcode.controls.DepositControl;
import org.firstinspires.ftc.teamcode.controls.LiftControl;

@TeleOp(name = "Teleop")
@SuppressWarnings("unused")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        config.navSamples = 20;

        waitForStart();

        new DriveControl();
        new DroneControl();
        new IntakeControl();
        new DepositControl();
        new LiftControl();
        new HoistControl();
//        new LiftController();
//        new IntakeController();

//        schedule(
//            drone.lock()
//        );
    }
}
