package org.firstinspires.ftc.teamcode.adaptations.roadrunner.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.adaptations.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.adaptations.roadrunner.OmniDrive;
import org.firstinspires.ftc.teamcode.adaptations.roadrunner.TankDrive;

@Disabled
public final class StrafeTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .splineTo(new Vector2d(30, 30), Math.PI / 2)
                        .splineTo(new Vector2d(60, 0), Math.PI)
                        .build());
        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .splineTo(new Vector2d(30, 30), Math.PI / 2)
                            .splineTo(new Vector2d(60, 0), Math.PI)
                            .build());
        } else if (TuningOpModes.DRIVE_CLASS.equals(OmniDrive.class)) {
            OmniDrive drive = new OmniDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .splineToLinearHeading(new Pose2d(23.5, 0, 0), 0)
                            .strafeTo(new Vector2d(23.5, 23.5))
                            .strafeTo(new Vector2d(23.5, -23.5))
                            .build());
        } else {
            throw new AssertionError();
        }
    }
}
