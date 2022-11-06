package org.firstinspires.ftc.teamcode.modules;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class DriveModule {
    public DriveModule(OpMode opMode) {
        opMode.subsystems.drive.setDefaultCommand(
            opMode.commands.drive.input(
                () -> +opMode.gamepad1.getLeftX(),
                () -> +opMode.gamepad1.getLeftY(),
                () -> +opMode.gamepad1.getRightX()
            )
        );
    }
}
