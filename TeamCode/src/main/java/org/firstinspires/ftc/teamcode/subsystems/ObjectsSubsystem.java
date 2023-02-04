package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.TensorflowObjectDetection;

public class ObjectsSubsystem extends HardwareSubsystem {
    private TensorflowObjectDetection tensorflow;

    private boolean enabled = false;

    public ObjectsSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        if (!enabled) return;

        tensorflow.update();

        String result = "none";

        if (tensorflow.recognitions != null && !tensorflow.recognitions.isEmpty()) {
            Recognition recognition = tensorflow.recognitions.get(0);

            result = String.format(
                "%s, %.1f%%",
                recognition.getLabel(),
                recognition.getConfidence() * 100
            );
        }

        telemetry.addData("Detect", result);
    }

    public void enable() {
        if (enabled) return;
        tensorflow = new TensorflowObjectDetection(hardware.frontWebcam);
        enabled = true;
    }

    public void disable() {
        if (!enabled) return;
        enabled = false;
    }
}
