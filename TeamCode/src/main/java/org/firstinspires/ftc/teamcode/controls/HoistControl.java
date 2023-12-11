package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;
import static org.firstinspires.ftc.teamcode.commands.Commands.hoist;

public class HoistControl {
    public HoistControl() {
        gamepad2.getGamepadButton(LEFT_BUMPER)
            .whileActiveContinuous(hoist.change(-0.25));

        gamepad2.getGamepadButton(RIGHT_BUMPER)
            .whileActiveContinuous(hoist.change(+0.25));

        gamepad2.getGamepadButton(DPAD_UP)
            .whenActive(hoist.up())
            .whenInactive(hoist.stop());
    }
}
