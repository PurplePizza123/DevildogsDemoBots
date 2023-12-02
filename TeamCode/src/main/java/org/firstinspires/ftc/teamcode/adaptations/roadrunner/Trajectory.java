package org.firstinspires.ftc.teamcode.adaptations.roadrunner;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;

public class Trajectory {
    public TrajectoryActionBuilder builder;

    public Trajectory(TrajectoryActionBuilder builder) {
        this.builder = builder;
    }

    public void splineTo(Pose2d pose) {
        builder = builder.splineTo(pose.position, pose.heading);
    }

    public void strafeToSplineHeading(Pose2d pose) {
        builder = builder.strafeToSplineHeading(pose.position, pose.heading);
    }

    public void strafeToLinearHeading(Pose2d pose) {
        builder = builder.strafeToLinearHeading(pose.position, pose.heading);
    }
}
