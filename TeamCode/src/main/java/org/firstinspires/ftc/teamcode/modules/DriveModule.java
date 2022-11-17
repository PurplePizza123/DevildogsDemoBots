package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem.DrivePower.HIGH;
import static org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem.DrivePower.LOW;
import static org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem.DrivePower.MEDIUM;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class DriveModule {
    public DriveModule(OpMode opMode) {
        opMode.subsystems.drive.setDefaultCommand(
            opMode.commands.drive.input(
                () -> opMode.gamepad1.getLeftX(),
                () -> opMode.gamepad1.getLeftY(),
                () -> opMode.gamepad1.getRightX()
            )
        );

        opMode.gamepad1.getGamepadButton(DPAD_DOWN)
            .whenActive(opMode.commands.drive.setDrivePower(LOW));

        opMode.gamepad1.getGamepadButton(DPAD_LEFT)
            .or(opMode.gamepad1.getGamepadButton(DPAD_RIGHT))
            .whenActive(opMode.commands.drive.setDrivePower(MEDIUM));

        opMode.gamepad1.getGamepadButton(DPAD_UP)
            .whenActive(opMode.commands.drive.setDrivePower(HIGH));
    }
}
