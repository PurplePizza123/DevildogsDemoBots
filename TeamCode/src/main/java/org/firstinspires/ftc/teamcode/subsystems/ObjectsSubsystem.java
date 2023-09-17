package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Hardware;

public class ObjectsSubsystem extends HardwareSubsystem {

    private boolean enabled = false;

    public ObjectsSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
    }

    public void enable() {
    }

    public void disable() {
    }
}
