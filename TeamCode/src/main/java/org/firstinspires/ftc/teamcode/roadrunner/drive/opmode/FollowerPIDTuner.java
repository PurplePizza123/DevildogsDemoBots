package org.firstinspires.ftc.teamcode.roadrunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

/*
 * Op mode for preliminary tuning of the follower PID coefficients (located in the drive base
 * classes). The robot drives in a DISTANCE-by-DISTANCE square indefinitely. Utilization of the
 * dashboard is recommended for this tuning routine. To access the dashboard, connect your computer
 * to the RC's WiFi network. In your browser, navigate to https://192.168.49.1:8080/dash if you're
 * using the RC phone or https://192.168.43.1:8080/dash if you are using the Control Hub. Once
 * you've successfully connected, start the program, and your robot will begin driving in a square.
 * You should observe the target position (green) and your pose estimate (blue) and adjust your
 * follower PID coefficients such that you follow the target position as accurately as possible.
 * If you are using SampleMecanumDrive, you should be tuning TRANSLATIONAL_PID and HEADING_PID.
 * If you are using SampleTankDrive, you should be tuning AXIAL_PID, CROSS_TRACK_PID, and HEADING_PID.
 * These coefficients can be tuned live in dashboard.
 */
@Disabled
@Config
@Autonomous(group = "drive")
public class FollowerPIDTuner extends LinearOpMode {
    public static double TILE_WIDTH = 23.5; // in

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-TILE_WIDTH, -TILE_WIDTH, 0);

        drive.setPoseEstimate(startPose);

        waitForStart();

        if (isStopRequested()) return;

        for (int j = 0 ; !isStopRequested() && j < 10; j++) {
            Pose2d lastPose = startPose;

            for (int i = 0; i < 4; i++) {
                drive.setPIDFCoefficients();

                TrajectorySequence sequence = drive.trajectorySequenceBuilder(lastPose)
                    .forward(TILE_WIDTH * 2)
                    .turn(Math.toRadians(90))
                    .build();

                lastPose = sequence.end();

                drive.followTrajectorySequence(sequence);
            }
        }
    }
}
