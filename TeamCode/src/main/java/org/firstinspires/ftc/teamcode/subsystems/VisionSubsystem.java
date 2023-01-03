package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.AprilTagDetector;

@Config
public class VisionSubsystem extends HardwareSubsystem {
    private final AprilTagDetector detector;

    public VisionSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        this.detector = new AprilTagDetector(hardware.visionWebcam);
    }

    @Override
    public void periodic() {
        this.detector.update();

        telemetry.addData(
            "Vision", "%s, %.1f fps, %d oms, %d pms",
            getDetectionLabel(),
            hardware.visionWebcam.getFps(),
            hardware.visionWebcam.getOverheadTimeMs(),
            hardware.visionWebcam.getPipelineTimeMs()
        );

        telemetry.update();
    }

    public int getDetectionId() {
        return this.detector.detection == null ? 0 : this.detector.detection.id;
    }

    public String getDetectionLabel() {
        return this.detector.detection == null ? "none" : String.valueOf(this.detector.detection.id + 1);
    }
}
