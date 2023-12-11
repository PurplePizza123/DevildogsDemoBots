package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

import com.arcrobotics.ftclib.command.button.Trigger;

public class LiftControl {
    public LiftControl() {
        new Trigger(() ->
            gamepad2.getTrigger(LEFT_TRIGGER) > 0.5
        ).whileActiveContinuous(lift.change(-0.25));

        new Trigger(() ->
            gamepad2.getTrigger(RIGHT_TRIGGER) > 0.5
        ).whileActiveContinuous(lift.change(+0.25));
    }
}
