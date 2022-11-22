package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.DriveModule;
import org.firstinspires.ftc.teamcode.modules.IntakeModule;
import org.firstinspires.ftc.teamcode.modules.LiftModule;
import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@TeleOp(name = "Teleop")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        MenuSubsystem.enabled = false;

        super.initialize();

        new DriveModule(this);
        new LiftModule(this);
        new IntakeModule(this);
    }
}
