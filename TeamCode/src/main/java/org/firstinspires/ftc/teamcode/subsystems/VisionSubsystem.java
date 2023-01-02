package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.AprilTagService;

@Config
public class VisionSubsystem extends HardwareSubsystem {
    private final AprilTagService aprilTagService;

    public VisionSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        this.aprilTagService = new AprilTagService(hardware.signalWebcam);
    }

    @Override
    public void periodic() {
        this.aprilTagService.update();

        telemetry.addData(
            "Vision", "%s, %.1f fps, %d oms, %d pms",
            getDetectionLabel(),
            hardware.signalWebcam.getFps(),
            hardware.signalWebcam.getOverheadTimeMs(),
            hardware.signalWebcam.getPipelineTimeMs()
        );

        telemetry.update();
    }

    public int getDetectionId() {
        return this.aprilTagService.detection == null ? 0 : this.aprilTagService.detection.id;
    }

    public String getDetectionLabel() {
        return this.aprilTagService.detection == null ? "none" : String.valueOf(this.aprilTagService.detection.id + 1);
    }
}
