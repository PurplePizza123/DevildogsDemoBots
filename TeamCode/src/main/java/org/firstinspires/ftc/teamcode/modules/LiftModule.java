package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class LiftModule {
    public LiftModule(OpMode opMode) {
        opMode.gamepad1
            .getGamepadButton(DPAD_UP)
            .whenPressed(opMode.commands.lift.up());
            //.whenInactive(opMode.commands.lift.stop());

        opMode.gamepad1
            .getGamepadButton(DPAD_DOWN)
            .whenPressed(opMode.commands.lift.down());
            //.whenInactive(opMode.commands.lift.stop());
    }
}
