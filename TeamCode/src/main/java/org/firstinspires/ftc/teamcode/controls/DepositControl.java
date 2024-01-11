package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;

import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

public class DepositControl {
    public DepositControl() {
        gamepad2.getGamepadButton(BACK)
            .and(gamepad2.getGamepadButton(X))
            .toggleWhenActive(deposit.open(), deposit.close());
    }
}
