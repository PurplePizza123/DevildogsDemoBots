package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;

import static java.lang.Double.NaN;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

@Config
public class NavSubsystem extends SubsystemBase {
    private static final double TILE_WIDTH = 23.5;
    private static final double TILE_WIDTH_HALF = TILE_WIDTH / 2;
    private static final double ROBOT_LENGTH = 12;
    private static final double ROBOT_WIDTH = 12;

    public Pose2d getStartPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * (side == NORTH ? 0.5 : 1.5) * TILE_WIDTH,
            alliance.sign * (3 * TILE_WIDTH - ROBOT_LENGTH / 2),
            alliance.sign * Math.toRadians(-90)
        );
    }

    public Pose2d getSpikeMarkPose(int detection) {
        return createPose(
            ((config.side == NORTH ? 0.5 : -1.5) * TILE_WIDTH) - config.alliance.sign * detection * TILE_WIDTH_HALF,
            config.alliance.sign * (detection == 0 ? 1 : 1.25) * TILE_WIDTH,
            Math.toRadians(config.alliance.sign * -90 - 45 * detection),
            Axial.FRONT
        );
    }

    public Pose2d getSpikeMarkTilePose() {
        return createPose(
            ((config.side == NORTH ? 0.5 : -1.5) * TILE_WIDTH),
            config.alliance.sign * 1.5 * TILE_WIDTH,
            Math.toRadians(config.alliance.sign * -90),
            Axial.CENTER
        );
    }

    public Pose2d getSpikeMarkTilePose2() {
        return createPose(
            ((config.side == NORTH ? 0.5 : -1.5) * TILE_WIDTH),
            config.alliance.sign * 1.5 * TILE_WIDTH,
            Math.toRadians(0),
            Axial.CENTER
        );
    }

    public Pose2d getBackdropPose(int detection) {
        return createPose(
            TILE_WIDTH * 2.4,
            (config.alliance.sign * 1.5 - 0.25 * detection) * TILE_WIDTH,
            Math.toRadians(0),
            Axial.FRONT
        );
    }

    public Pose2d getParkingPose() {
        return createPose(
            TILE_WIDTH * 2.4,
            config.alliance.sign * (config.side == NORTH ? 2.5 : 0.5) * TILE_WIDTH,
            Math.toRadians(0),
            Axial.FRONT
        );
    }

    public enum Axial {
        FRONT(0), CENTER(NaN), BACK(Math.PI);

        double heading = 0;

        Axial(double heading) {
            this.heading = heading;
        }
    }

    public enum Lateral {
        LEFT(+Math.PI / 2), CENTER(NaN), RIGHT(-Math.PI / 2);

        double heading = 0;

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
