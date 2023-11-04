package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.Y;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class IntakeController {
    public IntakeController(OpMode opMode) {
        opMode.gamepad2.getGamepadButton(Y)
            .whenActive(opMode.commands.intake.getPixels());
    }
}
