package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controllers.DriveController;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;
import org.firstinspires.ftc.teamcode.controllers.JunctionController;
import org.firstinspires.ftc.teamcode.controllers.LiftController;

@TeleOp(name = "Teleop")
@SuppressWarnings("unused")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        waitForStart();

        new DriveController(this);
        new LiftController(this);
        new IntakeController(this);
        new JunctionController(this);

        schedule(
            commands.lift.calibrate()
        );
    }
}
