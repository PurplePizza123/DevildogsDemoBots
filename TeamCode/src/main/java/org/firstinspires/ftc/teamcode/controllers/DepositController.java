package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class DepositController {
    public DepositController(OpMode opMode) {
        opMode.gamepad2.getGamepadButton(X)
            .whenActive(opMode.commands.deposit.open());
        opMode.gamepad2.getGamepadButton(A)
            .whenActive(opMode.commands.deposit.close());
    }
}