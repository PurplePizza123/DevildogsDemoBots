package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class DroneSubsystem extends HardwareSubsystem {
    public static double POWER = 1;

    public DroneSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData(
            "Drone", "%.2f vel",
            hardware.drone.getPosition()
        );
    }

    public void release() {
        hardware.drone.setPosition(0); //TODO find servo release position
    }
}
