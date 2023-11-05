package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class DroneController {
    public DroneController(OpMode opMode) {
        opMode.gamepad2.getGamepadButton(BACK)
                .and(opMode.gamepad2.getGamepadButton(B))
            .whenActive(opMode.commands.drone.release());
    }
}