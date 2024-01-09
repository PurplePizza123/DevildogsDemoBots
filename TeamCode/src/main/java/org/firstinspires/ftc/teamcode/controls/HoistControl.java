package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;
import static org.firstinspires.ftc.teamcode.commands.Commands.hoist;

import com.acmerobotics.dashboard.config.Config;

@Config
public class HoistControl {
    public static double INCREMENT = 0.25;

    public HoistControl() {
        gamepad2.getGamepadButton(LEFT_BUMPER)
            .whileActiveContinuous(hoist.change(-INCREMENT));

        gamepad2.getGamepadButton(RIGHT_BUMPER)
            .whileActiveContinuous(hoist.change(+INCREMENT));
    }
}
