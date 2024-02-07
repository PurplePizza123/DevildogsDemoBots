package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.START;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.X;
import static org.firstinspires.ftc.teamcode.commands.Commands.auto;
import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.opMode;

import com.arcrobotics.ftclib.command.button.Trigger;

public class BackdropControl {
    public BackdropControl() {
        new Trigger(
                () -> opMode.gamepad2.getLeftY() > 0
        ).whileActiveOnce(opMode.commands.junction.changeJunction(+1, +0));

        new Trigger(
                () -> opMode.gamepad2.getLeftY() < 0
        ).whileActiveOnce(opMode.commands.junction.changeJunction(-1, +0));

        new Trigger(
                () -> opMode.gamepad2.getRightY() > 0
        ).whileActiveOnce(opMode.commands.junction.changeJunction(+0, -1));

        new Trigger(
                () -> opMode.gamepad2.getRightY() < 0
        ).whileActiveOnce(opMode.commands.junction.changeJunction(+0, +1));
    }
}
