package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class VisionSubsystem extends SubsystemBase {
    private AprilTagProcessor aprilTag;

    private TfodProcessor tfod;

    private VisionPortal myVisionPortal;

    List<AprilTagDetection> detections;

    List<Recognition> recognitions;

    int recognitionId = 0;

    public VisionSubsystem() {
        aprilTag = new AprilTagProcessor.Builder()
                .build();

        tfod = new TfodProcessor.Builder()
                .build();

            myVisionPortal = new VisionPortal.Builder()
                    .setCamera(hardware.frontWebcam)
                    .addProcessors(tfod, aprilTag)
                    .build();

            myVisionPortal.setProcessorEnabled(aprilTag, true);
            myVisionPortal.setProcessorEnabled(tfod, true);
    }

    @Override
    public void periodic() {
        detections = aprilTag.getDetections();
        recognitions = tfod.getRecognitions();

        if (recognitions.size() > 0) {
            Recognition recognition = recognitions.get(0);

            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            if (x < 213) recognitionId = -1;
            else if (x > 426) recognitionId = 1;
            else recognitionId = 0;

            telemetry.addData(
                "Recognition (%d)",
                "%s, %.0f %%, %.0fx, %.0fy",
                recognitionId,
                recognition.getLabel(),
                recognition.getConfidence() * 100,
                x, y
            );
        }
    }

    public int getRecognitionId() {
        return recognitionId;
    }
}
