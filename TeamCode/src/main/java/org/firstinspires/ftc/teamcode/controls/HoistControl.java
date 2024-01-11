package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.Y;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;
import static org.firstinspires.ftc.teamcode.commands.Commands.hoist;

import com.acmerobotics.dashboard.config.Config;

@Config
public class HoistControl {
    public static double INCREMENT = 0.25;

    public HoistControl() {
        gamepad2.getGamepadButton(Y)
            .and(gamepad2.getGamepadButton(DPAD_UP))
            .whileActiveContinuous(hoist.change(+INCREMENT));

        gamepad2.getGamepadButton(Y)
            .and(gamepad2.getGamepadButton(DPAD_DOWN))
            .whileActiveContinuous(hoist.change(-INCREMENT));
    }
}
