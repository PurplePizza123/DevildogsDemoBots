package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Config
public class VisionSubsystem extends SubsystemBase {
    public static boolean recognitionEnabled = false;

    public static boolean detectionEnabled = false;

    private final TfodProcessor tfod;

    private final AprilTagProcessor aprilTag;

    private final VisionPortal visionPortal;

    List<Recognition> recognitions;

    public Recognition recognition;

    public int recognitionId = 0;

    List<AprilTagDetection> detections;

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
    }

    @Override
    public void periodic() {
        visionPortal.setProcessorEnabled(tfod, recognitionEnabled);
        visionPortal.setProcessorEnabled(aprilTag, detectionEnabled);

        telemetry.addData(
            "Vision",
            () -> String.format(
                "Recognition: %s, Detection: %s",
                recognitionEnabled,
                detectionEnabled
            )
        );

        processRecognitions();
        processDetections();
    }

    @SuppressLint("DefaultLocale")
    private void processRecognitions() {
        recognitions = tfod.getRecognitions();

        recognition = recognitions.size() > 0 ? recognitions.get(0) : null;

        double x = recognition == null ? 0 : (recognition.getLeft() + recognition.getRight()) / 2;
        double y = recognition == null ? 0 : (recognition.getTop()  + recognition.getBottom()) / 2;

        if ((config.alliance == Alliance.RED && config.side == Side.NORTH) ||
            (config.alliance == Alliance.BLUE && config.side == Side.SOUTH)) {
            if (recognition == null) recognitionId = -1;
            else if (x <= 350) recognitionId = 0;
            else recognitionId = 1;
        } else {
            if (recognition == null) recognitionId = 1;
            else if (x <= 393) recognitionId = -1;
            else recognitionId = 0;
        }

        if (recognition != null) {
            telemetry.addData(
                "Recognition",
                () -> String.format(
                    "%s, %.0f%%, %.0fx, %.0fy, %d",
                    recognition.getLabel(),
                    recognition.getConfidence() * 100,
                    x, y, recognitionId
                )
            );
        }
    }

    @SuppressLint("DefaultLocale")
    private void processDetections() {
        detections = aprilTag.getDetections();

        detection = detections.size() > 0 ? detections.get(0) : null;

        if (detection != null) {
            telemetry.addData(
                "Detection",
                () -> String.format(
                    "%d, %s, XYZ %.0f %.0f %.0f",
                    detection.id,
                    detection.metadata.name,
                    detection.ftcPose.x,
                    detection.ftcPose.y,
                    detection.ftcPose.z
                )
            );
        }
    }
}
