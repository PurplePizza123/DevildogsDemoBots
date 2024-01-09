package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Trigger;

@Config
public class LiftControl {
    public static double THRESHOLD = 0.5;

    public static double INCREMENT = 0.5;

    public LiftControl() {
        new Trigger(() ->
            gamepad2.getTrigger(LEFT_TRIGGER) > THRESHOLD
        ).whileActiveContinuous(lift.change(-INCREMENT));

        new Trigger(() ->
            gamepad2.getTrigger(RIGHT_TRIGGER) > THRESHOLD
        ).whileActiveContinuous(lift.change(+INCREMENT));
    }
}
