package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;

import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

import com.acmerobotics.dashboard.config.Config;

@Config
public class LiftControl {
    public static double INCREMENT = 0.5;

    public LiftControl() {
        gamepad2.getGamepadButton(X)
            .and(gamepad2.getGamepadButton(DPAD_UP))
            .whileActiveContinuous(lift.change(+INCREMENT));

        gamepad2.getGamepadButton(X)
            .and(gamepad2.getGamepadButton(DPAD_DOWN))
            .whileActiveContinuous(lift.change(-INCREMENT));
    }
}
