package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.Y;

import static org.firstinspires.ftc.teamcode.commands.Commands.intake;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

public class IntakeControl {
    public IntakeControl() {
        gamepad2.getGamepadButton(Y)
            .whenActive(intake.getPixels());

        gamepad2.getGamepadButton(B)
            .whenActive(intake.in())
            .whenInactive(intake.stop());

        gamepad2.getGamepadButton(DPAD_DOWN)
            .whenActive(intake.out())
            .whenInactive(intake.stop());
    }
}
