package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NavSubsystem extends HardwareSubsystem {
    private static final double TILE_WIDTH = 23.5;
    private static final double ROBOT_LENGTH = 14;

    public NavSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    public Pose2d getStartPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * 1.5 * TILE_WIDTH,
            alliance.sign * (3 * TILE_WIDTH - ROBOT_LENGTH / 2),
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
            side.sign * (3 * TILE_WIDTH - 2.5),
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

    public Pose2d[] getTransitionPoses(Pose2d start, Pose2d end, double stxo, double offset, boolean y1st) {
        Pose2d startTile = new Pose2d(
            nearestTile(start.getX(), 0.5 + stxo),
            nearestTile(start.getY(), 0.5)
        );

        Pose2d endTile = new Pose2d(
            nearestTile(end.getX() - startTile.getX(), 0) + startTile.getX(),
            nearestTile(end.getY() - startTile.getY(), 0) + startTile.getY()
        );

        Pose2d midTile = new Pose2d(
            y1st ? startTile.getX() : endTile.getX(),
            y1st ? endTile.getY() : startTile.getY()
        );

        double endHeading = Math.atan2(
            end.getY() - endTile.getY(),
            end.getX() - endTile.getX()
        );

        end = new Pose2d(
            end.getX() + Math.cos(endHeading) * offset,
            end.getY() + Math.sin(endHeading) * offset,
            endHeading
        );

        ArrayList<Pose2d> poses = new ArrayList<>(Collections.singletonList(start));

        for (Pose2d nextPose : Arrays.asList(startTile, midTile, endTile, end)) {
            Pose2d lastPose = poses.get(poses.size() - 1);
            if (lastPose.getX() == nextPose.getX() &&
                lastPose.getY() == nextPose.getY())
                    poses.remove(lastPose);
            poses.add(nextPose);
        }

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
        double tiles = Math.floor(Math.abs(value) / TILE_WIDTH) + offset;
        return sign * tiles * TILE_WIDTH;
    }
}
