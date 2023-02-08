package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.AprilTagDetector;

@Config
public class RandSubsystem extends HardwareSubsystem {
    private final AprilTagDetector detector;

    private boolean enabled = true;

    public RandSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        this.detector = new AprilTagDetector(hardware.frontWebcam);
    }

    @Override
    public void periodic() {
        if (enabled) this.detector.update();

        telemetry.addData(
            "Rand", "%s, %.1f fps, %d oms, %d pms",
            getDetectionLabel(),
            detector.webcam.getFps(),
            detector.webcam.getOverheadTimeMs(),
            detector.webcam.getPipelineTimeMs()
        );
    }

    public int getDetectionId() {
        return this.detector.detection == null ? 0 : clamp(this.detector.detection.id, 0, 2);
    }

    public String getDetectionLabel() {
        return this.detector.detection == null ? "none" : String.valueOf(this.detector.detection.id + 1);
    }

    public void disable() {
        detector.webcam.stopStreaming();
        enabled = false;
    }
}
