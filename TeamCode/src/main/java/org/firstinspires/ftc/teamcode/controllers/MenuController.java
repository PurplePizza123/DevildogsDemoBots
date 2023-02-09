package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import com.arcrobotics.ftclib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class MenuController {
    public MenuController(OpMode opMode) {
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

        opMode.gamepad1.getGamepadButton(BACK)
            .or(opMode.gamepad2.getGamepadButton(BACK))
            .and(new Trigger(
                () -> opMode.gamepad1.getTrigger(LEFT_TRIGGER) +
                    opMode.gamepad2.getTrigger(LEFT_TRIGGER) > 0
            )).whileActiveOnce(
                opMode.commands.menu.toggleAlliance()
            );

        opMode.gamepad1.getGamepadButton(BACK)
            .or(opMode.gamepad2.getGamepadButton(BACK))
            .and(new Trigger(
                () -> opMode.gamepad1.getTrigger(RIGHT_TRIGGER) +
                    opMode.gamepad2.getTrigger(RIGHT_TRIGGER) > 0
            )).whileActiveOnce(
                opMode.commands.menu.toggleSide()
            );

        opMode.gamepad1.getGamepadButton(BACK)
            .whenReleased(opMode.commands.menu.setStartPose());
    }
}
