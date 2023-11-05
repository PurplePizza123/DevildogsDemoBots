package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class DroneSubsystem extends SubsystemBase {
    @Override
    public void periodic() {
        telemetry.addData(
            "Drone", "%.2f vel",
            hardware.drone.getPosition()
        );
    }

    public void release() {
        // TODO: find servo release position
        hardware.drone.setPosition(0);
    }
}
