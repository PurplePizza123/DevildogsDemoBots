package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftModule {
    public LiftModule(OpMode opMode) {
        opMode.gamepad1.getGamepadButton(DPAD_RIGHT)
            .or(opMode.gamepad2.getGamepadButton(DPAD_RIGHT))
            .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftHeight.GROUND));

        opMode.gamepad1.getGamepadButton(DPAD_DOWN)
            .or(opMode.gamepad2.getGamepadButton(DPAD_DOWN))
            .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftHeight.LOW));

        opMode.gamepad1.getGamepadButton(DPAD_LEFT)
                .or(opMode.gamepad2.getGamepadButton(DPAD_LEFT))
                .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftHeight.MID));

        opMode.gamepad1.getGamepadButton(DPAD_UP)
                .or(opMode.gamepad2.getGamepadButton(DPAD_UP))
                .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftHeight.HIGH));

        opMode.gamepad1.getGamepadButton(X)
                .or(opMode.gamepad2.getGamepadButton(X))
                .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftHeight.INTAKE));
    }
}
