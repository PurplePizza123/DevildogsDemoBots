package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;

import org.firstinspires.ftc.teamcode.commands.AutonomousCommands;
import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class MenuModule {
    public MenuModule(OpMode opMode) {
        opMode.gamepad1.getGamepadButton(A)
            .or(opMode.gamepad2.getGamepadButton(A))
            .whenActive(opMode.commands.menu.setPlan(AutonomousCommands.Plan.A));

        opMode.gamepad1.getGamepadButton(B)
            .or(opMode.gamepad2.getGamepadButton(B))
            .whenActive(opMode.commands.menu.setPlan(AutonomousCommands.Plan.B));

        opMode.gamepad1.getGamepadButton(DPAD_DOWN)
            .or(opMode.gamepad2.getGamepadButton(DPAD_DOWN))
            .whenActive(opMode.commands.menu.changeDelay(-1));

        opMode.gamepad1.getGamepadButton(DPAD_UP)
            .or(opMode.gamepad2.getGamepadButton(DPAD_UP))
            .whenActive(opMode.commands.menu.changeDelay(+1));

        opMode.gamepad1.getGamepadButton(LEFT_BUMPER)
            .or(opMode.gamepad2.getGamepadButton(LEFT_BUMPER))
            .whenActive(opMode.commands.menu.changeStacks(-1));

        opMode.gamepad1.getGamepadButton(RIGHT_BUMPER)
            .or(opMode.gamepad2.getGamepadButton(RIGHT_BUMPER))
            .whenActive(opMode.commands.menu.changeStacks(+1));
    }
}
