package org.firstinspires.ftc.teamcode.controls;

import static org.firstinspires.ftc.teamcode.commands.Commands.backdrop;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

import com.arcrobotics.ftclib.command.button.Trigger;

public class BackdropControl {
    public BackdropControl() {
        new Trigger(
                () -> gamepad2.getLeftY() > 0
        ).whileActiveOnce(backdrop.changeAddress(+0, +1));

        new Trigger(
                () -> gamepad2.getLeftY() < 0
        ).whileActiveOnce(backdrop.changeAddress(+0, -1));

        new Trigger(
                () -> gamepad2.getRightX() > 0
        ).whileActiveOnce(backdrop.changeAddress(+1, +0));

        new Trigger(
                () -> gamepad2.getRightX() < 0
        ).whileActiveOnce(backdrop.changeAddress(-1, +0));
    }
}
