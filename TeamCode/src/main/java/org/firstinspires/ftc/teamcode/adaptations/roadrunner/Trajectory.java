package org.firstinspires.ftc.teamcode.adaptations.roadrunner;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;

public class Trajectory {
    public TrajectoryActionBuilder builder;

    public Trajectory(TrajectoryActionBuilder builder) {
        this.builder = builder;
    }

    public void turnTo(double heading) {
        builder = builder.turnTo(heading);
    }

    public void splineTo(Pose2d pose) {
        builder = builder.splineTo(pose.position, pose.heading);
    }

    public void strafeTo(Pose2d pose) {
        builder = builder.strafeTo(pose.position);
    }

    public void strafeToSplineHeading(Pose2d pose) {
        builder = builder.strafeToSplineHeading(pose.position, pose.heading);
    }

    public void strafeToLinearHeading(Pose2d pose) {
        builder = builder.strafeToLinearHeading(pose.position, pose.heading);
    }
}
