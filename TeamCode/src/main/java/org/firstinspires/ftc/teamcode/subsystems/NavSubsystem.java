package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.drive;
import static java.lang.Double.NaN;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;

/** @noinspection UnaryPlus*/
@Config
public class NavSubsystem extends SubsystemBase {
    private static final double TILE_WIDTH = 23.5;
    private static final double TILE_WIDTH_HALF = TILE_WIDTH / 2;
    private static final double ROBOT_LENGTH = 10.625;
    private static final double ROBOT_LENGTH_HALF = ROBOT_LENGTH / 2;
    private static final double ROBOT_WIDTH = 11.625;
    private static final double ROBOT_WIDTH_HALF = ROBOT_WIDTH / 2;

    public Pose2d getStartPose() {
        return new Pose2d(
                TILE_WIDTH * 2.5,
                TILE_WIDTH * -2.5,
                Math.toRadians(90)
        );
    }

    public Pose2d getForwardPose(double distance) {
        Pose2d pose = drive.getPose();
        double heading = pose.heading.toDouble();
        return createPose(
                Math.cos(heading) * distance + pose.position.x,
                Math.sin(heading) * distance + pose.position.y,
                heading
        );
    }

    public Pose2d getStrafePose(double distance) {
        Pose2d pose = drive.getPose();
        double heading = pose.heading.toDouble();
        double bearing = heading + Math.toRadians(90);
        return createPose(
                Math.cos(bearing) * distance + pose.position.x,
                Math.sin(bearing) * distance + pose.position.y,
                heading
        );
    }

    public enum Axial {
        FRONT(0), CENTER(NaN), BACK(Math.PI);

        public final double heading;

        Axial(double heading) {
            this.heading = heading;
        }
    }

    public enum Lateral {
        LEFT(+Math.PI / 2), CENTER(NaN), RIGHT(-Math.PI / 2);

        public final double heading;

        Lateral(double heading) {
            this.heading = heading;
        }
    }

    public Pose2d createPose(Pose2d pose, Axial axial) {
        return createPose(pose, axial, Lateral.CENTER);
    }

    public Pose2d createPose(Pose2d pose, Lateral lateral) {
        return createPose(pose, Axial.CENTER, lateral);
    }

    public Pose2d createPose(Pose2d pose, Axial axial, Lateral lateral) {
        return createPose(pose.position.x, pose.position.y, pose.heading.log(), axial, lateral);
    }

    public Pose2d createPose(double x, double y, double heading) {
        return createPose(x, y, heading, Axial.CENTER, Lateral.CENTER);
    }

    public Pose2d createPose(double x, double y, double heading, Axial axial) {
        return createPose(x, y, heading, axial, Lateral.CENTER);
    }

    public Pose2d createPose(double x, double y, double heading, Lateral lateral) {
        return createPose(x, y, heading, Axial.CENTER, lateral);
    }

    public Pose2d createPose(double x, double y, double heading, Axial axial, Lateral lateral) {
        if (axial != Axial.CENTER) {
            x -= Math.cos(normalizeHeading(heading + axial.heading)) * ROBOT_LENGTH / 2;
            y -= Math.sin(normalizeHeading(heading + axial.heading)) * ROBOT_LENGTH / 2;
        }

        if (lateral != Lateral.CENTER) {
            x -= Math.cos(normalizeHeading(heading + lateral.heading)) * ROBOT_WIDTH / 2;
            y -= Math.sin(normalizeHeading(heading + lateral.heading)) * ROBOT_WIDTH / 2;
        }

        return new Pose2d(x, y, heading);
    }

    private double normalizeHeading(double heading) {
        if (heading > +Math.PI) heading -= Math.PI * 2;
        if (heading < -Math.PI) heading += Math.PI * 2;
        return heading;
    }
}
