package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class DroneSubsystem extends SubsystemBase {
    public static double CLOSE = 0.17;
    public static double OPEN = 0.25;

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

    public void close() {
        hardware.drone.setPosition(CLOSE);
    }

    public void open() {
        hardware.drone.setPosition(OPEN);
    }
}
