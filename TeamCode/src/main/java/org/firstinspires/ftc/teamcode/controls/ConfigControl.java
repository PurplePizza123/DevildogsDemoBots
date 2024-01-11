package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_STICK_BUTTON;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.RIGHT_STICK_BUTTON;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.Y;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import static org.firstinspires.ftc.teamcode.commands.Commands.menu;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad1;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;
import static org.firstinspires.ftc.teamcode.subsystems.ConfigSubsystem.Change.NEXT;
import static org.firstinspires.ftc.teamcode.subsystems.ConfigSubsystem.Change.PREV;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.game.Backdrop;
import org.firstinspires.ftc.teamcode.subsystems.ConfigSubsystem;

@Config
public class ConfigControl {
    public static double THRESHOLD = 0.5;

    public static double DELAY_INCREMENT = 1;

    public static double OFFSET_INCREMENT = 0.5;

    public ConfigControl() {
        gamepad1.getGamepadButton(BACK)
            .or(gamepad2.getGamepadButton(BACK))
            .whenActive(menu.setEditable(true))
            .whenInactive(menu.setEditable(false));

        gamepad1.getGamepadButton(DPAD_DOWN)
            .or(gamepad2.getGamepadButton(DPAD_DOWN))
            .whenActive(menu.changeItem(NEXT));

        gamepad1.getGamepadButton(DPAD_UP)
            .or(gamepad2.getGamepadButton(DPAD_UP))
            .whenActive(menu.changeItem(PREV));

        gamepad1.getGamepadButton(DPAD_LEFT)
            .or(gamepad2.getGamepadButton(DPAD_LEFT))
            .whenActive(menu.changeValue(PREV));

        gamepad1.getGamepadButton(DPAD_RIGHT)
            .or(gamepad2.getGamepadButton(DPAD_RIGHT))
            .whenActive(menu.changeValue(NEXT));
        }
}
