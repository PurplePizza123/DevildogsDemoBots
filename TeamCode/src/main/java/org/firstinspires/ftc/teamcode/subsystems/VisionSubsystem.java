package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class VisionSubsystem extends SubsystemBase {
    private AprilTagProcessor aprilTag;

    private TfodProcessor tfod;

    private VisionPortal visionPortal;

    List<AprilTagDetection> detections;

    List<Recognition> recognitions;

    public Recognition recognition;

    public int recognitionId = -1;

    public AprilTagDetection detection;

    public VisionSubsystem() {
        aprilTag = new AprilTagProcessor.Builder().build();

        tfod = new TfodProcessor.Builder()
            .setModelFileName("/sdcard/FIRST/tflitemodels/centerstage.tflite")
            .setModelLabels(new String[]{"Team Prop"})
            .build();

        visionPortal = new VisionPortal.Builder()
            .setCamera(hardware.frontWebcam)
            .addProcessors(tfod, aprilTag)
            .build();

        visionPortal.setProcessorEnabled(aprilTag, true);
        visionPortal.setProcessorEnabled(tfod, true);
    }

    @Override
    public void periodic() {
        processDetections();
        processRecognitions();
    }

    private void processDetections() {
        detections = aprilTag.getDetections();

        detection = detections.size() > 0 ? detections.get(0) : null;

        if (detection != null) {
            telemetry.addData(
                "Detection",
                "%d, %s, XYZ %.0f %.0f %.0f",
                detection.id,
                detection.metadata.name,
                detection.ftcPose.x,
                detection.ftcPose.y,
                detection.ftcPose.z
            );
        }
    }

    private void processRecognitions() {
        recognitions = tfod.getRecognitions();

        if (recognitions.size() > 0) {
            recognition = recognitions.get(0);

            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            if (x <= 450) recognitionId = 0;
            else if (x > 450) recognitionId = 1;
            else recognitionId = -1;

            telemetry.addData(
                "Recognition",
                "%s, %.0f %%, %.0fx, %.0fy, %d",
                recognition.getLabel(),
                recognition.getConfidence() * 100,
                x, y, recognitionId
            );
        }
    }
}
