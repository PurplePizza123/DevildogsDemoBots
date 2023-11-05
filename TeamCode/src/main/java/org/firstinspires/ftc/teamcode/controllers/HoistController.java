package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class HoistController {
    public HoistController(OpMode opMode) {
        opMode.gamepad2.getGamepadButton(LEFT_BUMPER)
                .whenActive(opMode.commands.hoist.down())
                .whenInactive(opMode.commands.hoist.stop());
        opMode.gamepad2.getGamepadButton(RIGHT_BUMPER)
                .whenActive(opMode.commands.hoist.up())
                .whenInactive(opMode.commands.hoist.stop());

    }
}