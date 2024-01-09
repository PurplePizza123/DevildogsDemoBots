package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.DriveControl;
import org.firstinspires.ftc.teamcode.controls.DroneControl;
import org.firstinspires.ftc.teamcode.controls.HoistControl;
import org.firstinspires.ftc.teamcode.controls.DepositControl;
import org.firstinspires.ftc.teamcode.controls.IntakeControl;
import org.firstinspires.ftc.teamcode.controls.LiftControl;

/** @noinspection InstantiationOfUtilityClass*/
@TeleOp(name = "Teleop")
@SuppressWarnings("unused")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        waitForStart();

        new DriveControl();
        new IntakeControl();
        new LiftControl();
        new DepositControl();
        new HoistControl();
        new DroneControl();
    }
}
