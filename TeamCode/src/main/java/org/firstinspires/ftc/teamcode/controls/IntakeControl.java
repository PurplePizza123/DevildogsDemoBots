package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.Y;

import static org.firstinspires.ftc.teamcode.commands.Commands.intake;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

public class IntakeControl {
    public IntakeControl() {
        gamepad2.getGamepadButton(Y)
            .whenActive(intake.getPixels());
    }
}
