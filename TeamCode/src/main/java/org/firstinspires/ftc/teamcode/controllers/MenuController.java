package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_BUMPER;

import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.auto;

import com.arcrobotics.ftclib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class MenuController {
    public MenuController(OpMode opMode) {
        if (auto) {
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
        } else {
            new Trigger(
                () -> opMode.gamepad2.getLeftY() > 0
            ).whileActiveOnce(opMode.commands.menu.changeJunction(+1, +0));

            new Trigger(
                () -> opMode.gamepad2.getLeftY() < 0
            ).whileActiveOnce(opMode.commands.menu.changeJunction(-1, +0));

            new Trigger(
                () -> opMode.gamepad2.getRightY() > 0
            ).whileActiveOnce(opMode.commands.menu.changeJunction(+0, -1));

            new Trigger(
                () -> opMode.gamepad2.getRightY() < 0
            ).whileActiveOnce(opMode.commands.menu.changeJunction(+0, +1));
        }
    }
}
