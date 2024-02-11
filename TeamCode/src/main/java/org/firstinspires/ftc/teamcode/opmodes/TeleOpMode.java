package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

@TeleOp(name = "Teleop")
@SuppressWarnings("unused")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();
        Subsystems.vision.setActiveCamera(hardware.rearWebcam);
        waitForStart();
        Controls.initializeTeleop();
        hardware.lift.set(0);
    }
}
