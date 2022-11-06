package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class LiftModule {
    public LiftModule(OpMode opMode) {
        opMode.subsystems.lift.setDefaultCommand(opMode.commands.lift.stop());

        opMode.gamepad1
            .getGamepadButton(DPAD_UP)
            .whenActive(opMode.commands.lift.up());

        opMode.gamepad1
            .getGamepadButton(DPAD_DOWN)
            .whenActive(opMode.commands.lift.down());
    }
}
