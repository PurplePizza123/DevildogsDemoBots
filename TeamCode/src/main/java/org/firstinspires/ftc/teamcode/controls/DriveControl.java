package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;

import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad1;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class DriveControl {
    public DriveControl() {
        Subsystems.drive.setDefaultCommand(
            drive.input(
                () -> gamepad1.getLeftY(),
                () -> gamepad1.getLeftX(),
                () -> gamepad1.getRightX()
            )
        );

        gamepad1.getGamepadButton(DPAD_DOWN)
            .whenActive(drive.setDrivePower(0.33));

        gamepad1.getGamepadButton(DPAD_LEFT)
            .or(gamepad1.getGamepadButton(DPAD_RIGHT))
            .whenActive(drive.setDrivePower(0.67));

        gamepad1.getGamepadButton(DPAD_UP)
            .whenActive(drive.setDrivePower(1.00));

        gamepad1.getGamepadButton(BACK)
            .and(gamepad1.getGamepadButton(B))
            .toggleWhenActive(drive.toDroneLaunch(), drive.toDroneLaunchWithDetection());
    }
}
