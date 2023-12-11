package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_STICK_BUTTON;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.Y;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import static org.firstinspires.ftc.teamcode.commands.Commands.menu;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad1;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

import com.arcrobotics.ftclib.command.button.Trigger;

public class MenuControl {
    public MenuControl() {
        gamepad1.getGamepadButton(DPAD_DOWN)
            .or(gamepad2.getGamepadButton(DPAD_DOWN))
            .whenActive(menu.changeDelay(-1));

        gamepad1.getGamepadButton(DPAD_UP)
            .or(gamepad2.getGamepadButton(DPAD_UP))
            .whenActive(menu.changeDelay(+1));

        gamepad1.getGamepadButton(BACK)
            .or(gamepad2.getGamepadButton(BACK))
            .and(new Trigger(
                () -> gamepad1.getTrigger(LEFT_TRIGGER) +
                    gamepad2.getTrigger(LEFT_TRIGGER) > 0
            )).whileActiveOnce(
                menu.toggleAlliance()
            );

        gamepad1.getGamepadButton(BACK)
            .or(gamepad2.getGamepadButton(BACK))
            .and(new Trigger(
                () -> gamepad1.getTrigger(RIGHT_TRIGGER) +
                    gamepad2.getTrigger(RIGHT_TRIGGER) > 0
            )).whileActiveOnce(
                menu.toggleSide()
            );

        gamepad1.getGamepadButton(X)
            .and(gamepad1.getGamepadButton(BACK))
            .whenActive(menu.changeOffsetX(-0.5));

        gamepad1.getGamepadButton(B)
            .and(gamepad1.getGamepadButton(BACK))
            .whenActive(menu.changeOffsetX(+0.5));

        gamepad1.getGamepadButton(Y)
            .and(gamepad1.getGamepadButton(BACK))
            .whenActive(menu.changeOffsetY(+0.5));

        gamepad1.getGamepadButton(A)
            .and(gamepad1.getGamepadButton(BACK))
            .whenActive(menu.changeOffsetY(-0.5));

        gamepad1.getGamepadButton(BACK)
            .whenInactive(menu.setStartPose());

        gamepad2.getGamepadButton(LEFT_STICK_BUTTON)
                .whenActive(menu.toggleParking());

        gamepad2.getGamepadButton(RIGHT_STICK_BUTTON)
                .whenActive(menu.toggleBackdrop());
    }
}
