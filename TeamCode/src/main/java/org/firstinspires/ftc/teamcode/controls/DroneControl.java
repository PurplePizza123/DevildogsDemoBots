package org.firstinspires.ftc.teamcode.controls;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.B;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.BACK;

import static org.firstinspires.ftc.teamcode.commands.Commands.drone;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad1;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.gamepad2;

public class DroneControl {
    public DroneControl() {
        gamepad2.getGamepadButton(BACK)
            .and(gamepad2.getGamepadButton(B))
            .whenActive(drone.release());
    }
}
