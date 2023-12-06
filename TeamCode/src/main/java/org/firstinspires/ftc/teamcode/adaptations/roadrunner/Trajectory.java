package org.firstinspires.ftc.teamcode.adaptations.roadrunner;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;

/** @noinspection unused*/
public class Trajectory {
    public TrajectoryActionBuilder builder;

    public Trajectory(TrajectoryActionBuilder builder) {
        this.builder = builder;
    }

    public void strafeTo(Pose2d pose) {
        builder = builder.strafeTo(pose.position);
    }

    public void strafeToSplineHeading(Pose2d pose) {
        builder = builder.strafeToSplineHeading(pose.position, pose.heading);
    }

    public void strafeToConstantHeading(Pose2d pose) {
        builder = builder.strafeToConstantHeading(pose.position);
    }

    public void strafeToLinearHeading(Pose2d pose) {
        builder = builder.strafeToLinearHeading(pose.position, pose.heading);
    }

    public void splineTo(Pose2d pose) {
        builder = builder.splineTo(pose.position, pose.heading);
    }

    public void splineToSplineHeading(Pose2d pose) {
        builder = builder.splineToSplineHeading(pose, pose.heading);
    }

    public void splineToConstantHeading(Pose2d pose) {
        builder = builder.splineToConstantHeading(pose.position, pose.heading);
    }

    public void splineToLinearHeading(Pose2d pose) {
        builder = builder.splineToLinearHeading(pose, pose.heading);
    }

    public void lineToX(double x) {
        builder = builder.lineToX(x);
    }

    public void lineToXSplineHeading(double x, double heading) {
        builder = builder.lineToXSplineHeading(x, heading);
    }
    public void lineToXConstantHeading(double x) {
        builder = builder.lineToXConstantHeading(x);
    }

    public void lineToXLinearHeading(double x, double heading) {
        builder = builder.lineToXLinearHeading(x, heading);
    }

    public void lineToY(double x) {
        builder = builder.lineToX(x);
    }

    public void lineToYSplineHeading(double x, double heading) {
        builder = builder.lineToXSplineHeading(x, heading);
    }
    public void lineToYConstantHeading(double x) {
        builder = builder.lineToXConstantHeading(x);
    }

    public void lineToYLinearHeading(double x, double heading) {
        builder = builder.lineToXLinearHeading(x, heading);
    }

    public void turnTo(double heading) {
        builder = builder.turnTo(heading);
    }

    public void setTangent(double r) {
        builder = builder.setTangent(r);
    }

    public void setReversed(boolean reversed) {
        builder = builder.setReversed(reversed);
    }
}
