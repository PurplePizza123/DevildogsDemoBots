package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
import org.firstinspires.ftc.teamcode.hacks.Offsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

@Config
public class NavSubsystem extends HardwareSubsystem {
    private static final double TILE_WIDTH = 23.5;
    private static final double TILE_WIDTH_HALF = TILE_WIDTH / 2;
    private static final double ROBOT_LENGTH = 15.25;

    public NavSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    public Pose2d getStartJunction(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * 1 * TILE_WIDTH,
            alliance.sign * 2 * TILE_WIDTH,
            alliance.sign * Math.toRadians(-90) + side.sign * Math.toRadians(90)
        );
    }

    public Pose2d getStartPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * (1.5 * TILE_WIDTH - config.offsetX),
            alliance.sign * (3 * TILE_WIDTH - ROBOT_LENGTH / 2 - config.offsetY),
            alliance.sign * Math.toRadians(-90)
        );
    }

    public Pose2d getTilePose(String label) {
        return new Pose2d(
            +(label.charAt(1) - (('3' + '4') / 2d)) * TILE_WIDTH,
            -(label.charAt(0) - (('C' + 'D') / 2d)) * TILE_WIDTH
        );
    }

    public Pose2d getJunctionPose(String label) {
        return new Pose2d(
            +(label.charAt(1) - '3') * TILE_WIDTH,
            -(label.charAt(0) - 'X') * TILE_WIDTH
        );
    }

    public Pose2d getStackPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * (3 * TILE_WIDTH - 1.75),
            alliance.sign * 0.5 * TILE_WIDTH
        );
    }

    public Pose2d getSubstationPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * 0.25 * TILE_WIDTH,
            alliance.sign * 2.75 * TILE_WIDTH
        );
    }

    public Pose2d getTerminalPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * 2.75 * TILE_WIDTH,
            alliance.sign * -side.sign * 2.75 * TILE_WIDTH
        );
    }

    public Pose2d[] getTransitionPoses(Pose2d start, Pose2d end, Consumer<Offsets>... consumers) {
        Offsets offsets = new Offsets();

        for (Consumer<Offsets> consumer : consumers) {
            consumer.accept(offsets);
        }

        start = new Pose2d(
            start.getX() + offsets.startX,
            start.getY() + offsets.startY,
            start.getHeading()
        );

        Pose2d startTile = new Pose2d(
            nearestTile(start.getX(), + TILE_WIDTH_HALF + offsets.startTileX),
            nearestTile(start.getY(), + TILE_WIDTH_HALF + offsets.startTileY)
        );

        Pose2d endTile = new Pose2d(
            nearestTile(end.getX() - startTile.getX(), offsets.endTileX) + startTile.getX(),
            nearestTile(end.getY() - startTile.getY(), offsets.endTileY) + startTile.getY()
        );

        Pose2d midTile = new Pose2d(
            (offsets.y1st ? startTile.getX() : endTile.getX()) + offsets.midTileX,
            (offsets.y1st ? endTile.getY() : startTile.getY()) + offsets.midTileY
        );

        double endHeading = Math.atan2(
            end.getY() - endTile.getY(),
            end.getX() - endTile.getX()
        );

        end = new Pose2d(
            end.getX() + Math.cos(endHeading) * offsets.endX,
            end.getY() + Math.sin(endHeading) * offsets.endY,
            endHeading
        );

        ArrayList<Pose2d> poses = new ArrayList<>(Collections.singletonList(start));

        for (Pose2d nextPose : Arrays.asList(startTile, midTile, endTile, end)) {
            Pose2d lastPose = poses.get(poses.size() - 1);
            if ((lastPose.getX() == start.getX() && lastPose.getY() == start.getY()) ||
                (lastPose.getX() == nextPose.getX() && lastPose.getY() == nextPose.getY()))
                    poses.remove(lastPose);
            poses.add(nextPose);
        }

        poses.add(0, start);

        updatePoseHeadings(poses);

        return poses.toArray(new Pose2d[0]);
    }

    private static void updatePoseHeadings(ArrayList<Pose2d> poses) {
        for (int i = 1; i < poses.size(); i++) {
            Pose2d prev = poses.get(i - 1);
            Pose2d curr = poses.get(i);

            poses.set(i, new Pose2d(
                curr.getX(),
                curr.getY(),
                Math.atan2(
                    curr.getY() - prev.getY(),
                    curr.getX() - prev.getX()
                )
            ));
        }
    }

    private static double nearestTile(double value, double offset) {
        double sign = value == 0 ? 1 : value / Math.abs(value);
        double tiles = Math.floor(Math.abs(value) / TILE_WIDTH) + offset / TILE_WIDTH;
        return sign * tiles * TILE_WIDTH;
    }
}
