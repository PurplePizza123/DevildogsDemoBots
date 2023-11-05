package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_STICK_BUTTON;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_STICK_BUTTON;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import com.arcrobotics.ftclib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class LiftController {
    public LiftController(OpMode opMode) {
        opMode.gamepad2.getGamepadButton(LEFT_STICK_BUTTON)
            .and(opMode.gamepad2.getGamepadButton(RIGHT_STICK_BUTTON))
            .whenActive(opMode.commands.lift.calibrate());

        new Trigger(() ->
            opMode.gamepad2.getTrigger(LEFT_TRIGGER) > 0.5
        ).whileActiveContinuous(opMode.commands.lift.change(-0.5));

        new Trigger(() ->
            opMode.gamepad2.getTrigger(RIGHT_TRIGGER) > 0.5
        ).whileActiveContinuous(opMode.commands.lift.change(+0.5));
    }
}
