package org.firstinspires.ftc.teamcode.controllers;

import com.arcrobotics.ftclib.command.button.Trigger;

import org.firstinspires.ftc.teamcode.opmodes.OpMode;

public class JunctionController {
    public JunctionController(OpMode opMode) {
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
