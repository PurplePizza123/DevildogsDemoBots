package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.DriveModule;
import org.firstinspires.ftc.teamcode.modules.IntakeModule;
import org.firstinspires.ftc.teamcode.modules.LiftModule;

@TeleOp(name = "Teleop")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        subsystems.menu.enabled = false;

        new DriveModule(this);
        new LiftModule(this);
        new IntakeModule(this);
    }
}
