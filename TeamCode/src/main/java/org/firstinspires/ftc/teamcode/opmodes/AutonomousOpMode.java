package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.DriveModule;
import org.firstinspires.ftc.teamcode.modules.IntakeModule;
import org.firstinspires.ftc.teamcode.modules.LiftModule;

@Autonomous
public class AutonomousOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        schedule(
            commands.drive.move(0, 1, 0, 4 * 24)
        );
    }
}
