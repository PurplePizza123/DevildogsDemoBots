package org.firstinspires.ftc.teamcode.controllers;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.Y;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class DriveController {
    public DriveController(OpMode opMode) {
        opMode.subsystems.drive.setDefaultCommand(
            opMode.commands.drive.input(
                () -> opMode.gamepad1.getLeftX(),
                () -> opMode.gamepad1.getLeftY(),
                () -> opMode.gamepad1.getRightX()
            )
        );

        opMode.gamepad1.getGamepadButton(DPAD_DOWN)
            .whenActive(opMode.commands.drive.setDrivePower(0.25));

        opMode.gamepad1.getGamepadButton(DPAD_LEFT)
            .or(opMode.gamepad1.getGamepadButton(DPAD_RIGHT))
            .whenActive(opMode.commands.drive.setDrivePower(0.50));

        opMode.gamepad1.getGamepadButton(DPAD_UP)
            .whenActive(opMode.commands.drive.setDrivePower(0.75));

        opMode.gamepad1.getGamepadButton(A)
            .whenActive(opMode.commands.drive.toSubstation(config.alliance, config.side));

        opMode.gamepad1.getGamepadButton(Y)
            .whenActive(opMode.commands.drive.toJunction());

        opMode.gamepad1.getGamepadButton(B)
            .whenActive(opMode.commands.drive.toStack(config.alliance, config.side));
    }
}
