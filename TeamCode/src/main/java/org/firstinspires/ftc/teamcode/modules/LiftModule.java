package org.firstinspires.ftc.teamcode.modules;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_DOWN;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_LEFT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_RIGHT;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.DPAD_UP;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.GROUND;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.HIGH;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.INTAKE;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.LOW;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.MID;

import com.arcrobotics.ftclib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class LiftModule {
    public LiftModule(OpMode opMode) {
        new Trigger(() ->
            opMode.gamepad1.getTrigger(LEFT_TRIGGER) > 0.5 ||
            opMode.gamepad2.getTrigger(LEFT_TRIGGER) > 0.5
        ).whileActiveContinuous(opMode.commands.lift.down());

        new Trigger(() ->
            opMode.gamepad1.getTrigger(RIGHT_TRIGGER) > 0.5 ||
            opMode.gamepad2.getTrigger(RIGHT_TRIGGER) > 0.5
        ).whileActiveContinuous(opMode.commands.lift.up());

        opMode.gamepad2.getGamepadButton(DPAD_RIGHT)
            .or(opMode.gamepad1.getGamepadButton(DPAD_RIGHT))
            .whenActive(opMode.commands.lift.to(GROUND));

        opMode.gamepad2.getGamepadButton(DPAD_DOWN)
            .or(opMode.gamepad1.getGamepadButton(DPAD_DOWN))
            .whenActive(opMode.commands.lift.to(LOW));

        opMode.gamepad2.getGamepadButton(DPAD_LEFT)
            .or(opMode.gamepad1.getGamepadButton(DPAD_LEFT))
            .whenActive(opMode.commands.lift.to(MID));

        opMode.gamepad2.getGamepadButton(DPAD_UP)
            .or(opMode.gamepad1.getGamepadButton(DPAD_UP))
            .whenActive(opMode.commands.lift.to(HIGH));

        opMode.gamepad2.getGamepadButton(X)
            .or(opMode.gamepad1.getGamepadButton(X))
            .whenActive(opMode.commands.lift.to(INTAKE));
    }
}
