package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class DroneSubsystem extends SubsystemBase {
    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Drone",
            () -> String.format(
                "%.2f pos",
                hardware.drone.getPosition()
            )
        );
    }

    public void lock() {
        hardware.drone.setPosition(0.25);
    }

    public void release() {
        hardware.drone.setPosition(0.45);
    }
}
