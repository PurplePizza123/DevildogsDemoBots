package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_STICK_BUTTON;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_STICK_BUTTON;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

import com.arcrobotics.ftclib.command.button.Trigger;

public class LiftControl {
    public LiftControl() {
//        gamepad2.getGamepadButton(LEFT_STICK_BUTTON)
//            .and(gamepad2.getGamepadButton(RIGHT_STICK_BUTTON))
//            .whenActive(lift.calibrate());

        new Trigger(() ->
            gamepad2.getTrigger(LEFT_TRIGGER) > 0.5
        ).whileActiveContinuous(lift.change(-0.5));

        new Trigger(() ->
            gamepad2.getTrigger(RIGHT_TRIGGER) > 0.5
        ).whileActiveContinuous(lift.change(+0.5));
    }
}
