package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;
import android.util.Log;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.adaptations.ftcdashboard.FtcDashboardProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.HashMap;
import java.util.List;

@Config
public class VisionSubsystem extends SubsystemBase {
    public static double CAMERA_X_INCHES = 0;

    public static double CAMERA_Y_INCHES = 6.5;

    public static boolean recognitionEnabled = true;

    public static boolean detectionEnabled = true;

    private final TfodProcessor tfod;

    private final AprilTagProcessor aprilTag;

    private final VisionPortal visionPortal;

    List<Recognition> recognitions;

    public Recognition recognition;

    public int recognitionId = 0;
    public double recognitionX = 0;

    List<AprilTagDetection> detections;

    public AprilTagDetection detection;

    public com.acmerobotics.roadrunner.Pose2d detectionPose;

    private static final HashMap<Integer, Pose2d> aprilTagPoses = new HashMap<Integer, Pose2d>() {{
        put(1, new Pose2d(62, 41.5, Rotation2d.fromDegrees(180)));
        put(2, new Pose2d(62, 35.5, Rotation2d.fromDegrees(180)));
        put(3, new Pose2d(62, 29.5, Rotation2d.fromDegrees(180)));
        put(4, new Pose2d(62, -29.5, Rotation2d.fromDegrees(180)));
        put(5, new Pose2d(62, -35.5, Rotation2d.fromDegrees(180)));
        put(6, new Pose2d(62, -41.5, Rotation2d.fromDegrees(180)));
        put(7, new Pose2d(-70.5, -34.875, Rotation2d.fromDegrees(0)));
        put(8, new Pose2d(-70.5, -34.875, Rotation2d.fromDegrees(0)));
        put(9, new Pose2d(-70.5, 34.875, Rotation2d.fromDegrees(0)));
        put(10, new Pose2d(-70.5, 34.875, Rotation2d.fromDegrees(0)));
    }};

    @SuppressLint("SdCardPath")
    public VisionSubsystem() {
        FtcDashboardProcessor ftcDashboard = new FtcDashboardProcessor();

        aprilTag = new AprilTagProcessor.Builder().build();

        //noinspection SpellCheckingInspection
        tfod = new TfodProcessor.Builder()
            .setModelFileName("/sdcard/FIRST/tflitemodels/centerstage.tflite")
            .setModelLabels(new String[]{"Team Prop"})
            .build();

        visionPortal = new VisionPortal.Builder()
            .setCamera(hardware.frontWebcam)
            .addProcessors(ftcDashboard, tfod, aprilTag)
            .build();

        FtcDashboard.getInstance().startCameraStream(ftcDashboard, 0);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        visionPortal.setProcessorEnabled(tfod, recognitionEnabled);
        visionPortal.setProcessorEnabled(aprilTag, detectionEnabled);

        processRecognitions();
        processDetections();

        telemetry.addData(
            "Vision (State)",
            () -> String.format(
                "Recognition: %s, Detection: %s",
                recognitionEnabled,
                detectionEnabled
            )
        );

        telemetry.addData(
            "Vision (Recognition)",
            () -> recognition != null ? String.format(
                "%.0f%%, %d, %.1fx",
                recognition.getConfidence() * 100,
                recognitionId,
                recognitionX
            ) : "None"
        );

        telemetry.addData(
            "Vision (Detection)",
            () -> detection != null ? String.format(
                "%d, %s, %.1fx, %.1fy, %.1fz",
                detection.id,
                detection.metadata.name,
                detection.ftcPose.x,
                detection.ftcPose.y,
                detection.ftcPose.z
            ) : "None"
        );

        telemetry.addData(
            "Vision (Detection Pose)",
            () -> detectionPose != null ? String.format(
                "%.1fx, %.1fy, %.1f°",
                detectionPose.position.x,
                detectionPose.position.y,
                Math.toDegrees(
                    detectionPose.heading.toDouble()
                )
            ) : "None"
        );
    }

    @SuppressLint("DefaultLocale")
    public void logRecognition() {
        Log.i(
            "VisionSubsystem",
            "Recognition: " + (recognition != null ?
                String.format(
                    "%.0f%%, %d, %.1fx",
                    recognition.getConfidence() * 100,
                    recognitionId,
                    recognitionX
                ) : "None"
            )
        );
    }

    @SuppressLint("DefaultLocale")
    public void logDetection() {
        Log.i(
            "VisionSubsystem",
            "Detection: " + (detection != null ?
                String.format(
                    "%d, %s %.1fx, %.1fy, %.1fz, %.1fyaw",
                    detection.id,
                    detection.metadata.name,
                    detection.ftcPose.x,
                    detection.ftcPose.y,
                    detection.ftcPose.z,
                    detection.ftcPose.yaw
                ) : "None"
            )
        );

        Log.i(
            "VisionSubsystem",
            "Detection Pose: " + (detectionPose != null ?
                String.format(
                    "%.1fx, %.1fy, %.1f°",
                    detectionPose.position.x,
                    detectionPose.position.y,
                    Math.toDegrees(
                        detectionPose.heading.toDouble()
                    )
                ) : "None"
            )
        );
    }

    private void processRecognitions() {
        recognitions = tfod.getRecognitions();

        recognition = recognitions.size() > 0 ? recognitions.get(0) : null;

        recognitionX = recognition == null ? 0 : (recognition.getLeft() + recognition.getRight()) / 2;

        if ((config.alliance == RED && config.side == NORTH) ||
            (config.alliance == BLUE && config.side == SOUTH)) {
            if (recognitionX >= 375) recognitionId = 1;
            else if (recognitionX >= 50) recognitionId = 0;
            else recognitionId = -1;
        } else {
            if (recognitionX >= 600) recognitionId = 1;
            else if (recognitionX >= 375) recognitionId = 0;
            else recognitionId = -1;
        }
    }

    @SuppressLint("DefaultLocale")
    private void processDetections() {
        detections = aprilTag.getDetections();

        detection = detections.size() > 0 ? detections.get(0) : null;

        if (detection == null) {
            detectionPose = null;
            return;
        }

        Pose2d aprilTagPose = aprilTagPoses.get(detection.id);

        if (aprilTagPose == null) return;

        Translation2d rotatedPose = new Translation2d(
            detection.ftcPose.x + CAMERA_X_INCHES,
            detection.ftcPose.y + CAMERA_Y_INCHES
        ).rotateBy(
            Rotation2d.fromDegrees(
                90 - aprilTagPose.getRotation().getDegrees() - detection.ftcPose.yaw
            )
        );

        detectionPose = new com.acmerobotics.roadrunner.Pose2d(
            aprilTagPose.getX() - rotatedPose.getX(),
            aprilTagPose.getY() - rotatedPose.getY(),
            Math.PI + aprilTagPose.getHeading() - Math.toRadians(detection.ftcPose.yaw)
        );
    }
}
