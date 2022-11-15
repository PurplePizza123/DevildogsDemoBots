package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import com.arcrobotics.ftclib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class LiftModule {
    public LiftModule(OpMode opMode) {
        new Trigger(
            () -> opMode.gamepad1.getTrigger(LEFT_TRIGGER) > 0.5 ||
                  opMode.gamepad1.getTrigger(LEFT_TRIGGER) > 0.5
        ).whileActiveContinuous(opMode.commands.lift.down());

        new Trigger(
            () -> opMode.gamepad1.getTrigger(RIGHT_TRIGGER) > 0.5 ||
                  opMode.gamepad1.getTrigger(RIGHT_TRIGGER) > 0.5
        ).whileActiveContinuous(opMode.commands.lift.up());

        opMode.gamepad1.getGamepadButton(DPAD_RIGHT)
            .or(opMode.gamepad2.getGamepadButton(DPAD_RIGHT))
            .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftPosition.GROUND));

        opMode.gamepad1.getGamepadButton(DPAD_DOWN)
            .or(opMode.gamepad2.getGamepadButton(DPAD_DOWN))
            .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftPosition.LOW));

        opMode.gamepad1.getGamepadButton(DPAD_LEFT)
            .or(opMode.gamepad2.getGamepadButton(DPAD_LEFT))
            .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftPosition.MID));

        opMode.gamepad1.getGamepadButton(DPAD_UP)
            .or(opMode.gamepad2.getGamepadButton(DPAD_UP))
            .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftPosition.HIGH));

        opMode.gamepad1.getGamepadButton(X)
            .or(opMode.gamepad2.getGamepadButton(X))
            .whenActive(opMode.commands.lift.to(LiftSubsystem.LiftPosition.INTAKE));
    }
}
