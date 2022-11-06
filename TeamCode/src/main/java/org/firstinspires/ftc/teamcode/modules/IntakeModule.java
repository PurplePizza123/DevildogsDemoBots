package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class IntakeModule {
    public IntakeModule(OpMode opMode) {
        opMode.gamepad1
            .getGamepadButton(LEFT_BUMPER)
            .whenActive(opMode.commands.intake.in())
            .whenInactive(opMode.commands.intake.stop());

        opMode.gamepad1
            .getGamepadButton(RIGHT_BUMPER)
            .whenActive(opMode.commands.intake.out())
            .whenInactive(opMode.commands.intake.stop());
    }
}
