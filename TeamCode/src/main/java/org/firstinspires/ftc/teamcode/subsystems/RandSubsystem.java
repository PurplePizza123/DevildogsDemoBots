package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class RandSubsystem extends HardwareSubsystem {

    public RandSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
    }

    public int getDetectionId() {
        return 0;
    }

    public String getDetectionLabel() {
        return "";
    }

    public void enable() {
    }

    public void disable() {
    }
}
