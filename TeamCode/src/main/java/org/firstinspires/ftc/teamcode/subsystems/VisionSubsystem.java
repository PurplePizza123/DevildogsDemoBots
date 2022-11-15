package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.vision.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Config
public class VisionSubsystem extends HardwareSubsystem{
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    public static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    public static double fx = 578.272;
    public static double fy = 578.272;
    public static double cx = 402.145;
    public static double cy = 221.506;

    // UNITS ARE METERS
    public static double tagsize = 0.166;

    public static int numFramesWithoutDetection = 0;

    public static float DECIMATION_HIGH = 3;
    public static float DECIMATION_LOW = 2;
    public static float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    public static int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;

    private ArrayList<AprilTagDetection> detections;
    private AprilTagDetection detection;

    public VisionSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);

        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        hardware.webcam.setPipeline(aprilTagDetectionPipeline);

        hardware.webcam.openCameraDeviceAsync(
            new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() { hardware.webcam.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT); }

                @Override
                public void onError(int errorCode) { }
            }
        );

        FtcDashboard.getInstance().startCameraStream(hardware.webcam, 0);
    }

    @Override
    public void periodic() {
        detections = aprilTagDetectionPipeline.getDetectionsUpdate();

        if (detections != null && detections.size() != 0) {
            detection = detections.get(0);
        }

        telemetry.addData("Vision", "%d, %.1f fps, %d oms, %d pms", getDetectionId(), hardware.webcam.getFps(), hardware.webcam.getOverheadTimeMs(), hardware.webcam.getPipelineTimeMs());

        telemetry.update();
    }

    public void detect() {
        if (detections == null || detections.size() == 0 || detection != null) return;
        detection = detections.get(0);
    }

    public int getDetectionId() {
        return detection == null ? 0 : detection.id + 1;
    }
}
