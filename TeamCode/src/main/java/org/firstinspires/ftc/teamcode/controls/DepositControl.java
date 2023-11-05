package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;

import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

public class DepositControl {
    public DepositControl() {
        gamepad2.getGamepadButton(X)
            .whenActive(deposit.open());

        gamepad2.getGamepadButton(A)
            .whenActive(deposit.close());
    }
}
