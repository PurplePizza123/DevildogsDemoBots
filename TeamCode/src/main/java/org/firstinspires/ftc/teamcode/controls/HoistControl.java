package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;

import static org.firstinspires.ftc.teamcode.commands.Commands.hoist;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

public class HoistControl {
    public HoistControl() {
        gamepad2.getGamepadButton(LEFT_BUMPER)
            .whenActive(hoist.down())
            .whenInactive(hoist.stop());

        gamepad2.getGamepadButton(RIGHT_BUMPER)
            .whenActive(hoist.up())
            .whenInactive(hoist.stop());
    }
}
