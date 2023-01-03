package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.Arrays;

public class MeepMeepTesting {
    public static double TILE_WIDTH = 23.062;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            .setDimensions(14.75, 14)
            .setConstraints(45, 45, Math.toRadians(200), Math.toRadians(200), 12.82)
            .followTrajectorySequence(
                drive -> {
                    Pose2d start = getTilePose("A1");
                    Pose2d end = getJunctionPose("V5");
                    Pose2d[] poses = getTransitionPoses(start, end, false);
                    TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(start);
                    Arrays.stream(poses).forEach(builder::lineToLinearHeading);
                    return builder.build();
                }
            );

        meepMeep
            .setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
            .setDarkMode(true)
            .setBackgroundAlpha(0.95f)
            .addEntity(myBot)
            .start();
    }

    public static Pose2d getTilePose(String label){
        return new Pose2d(
            +(label.charAt(1) - (('3' + '4') / 2d)) * TILE_WIDTH,
            -(label.charAt(0) - (('C' + 'D') / 2d)) * TILE_WIDTH
        );
    }

    public static Pose2d getJunctionPose(String label){
        return new Pose2d(
            +(label.charAt(1) - '3') * TILE_WIDTH,
            -(label.charAt(0) - 'X') * TILE_WIDTH
        );
    }

    public static Pose2d[] getTransitionPoses(Pose2d start, Pose2d end, boolean y1st){
        Pose2d startTile = new Pose2d(
            nearestTile(start.getX(), 0.5),
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

        end = new Pose2d(
            end.getX(),
            end.getY(),
            Math.atan2(end.getY() - midTile.getY(), end.getX() - midTile.getX())
        );

        return new Pose2d[] { midTile, end };
    }

    private static double nearestTile(double value, double offset) {
        double sign = value == 0 ? 1 : value / Math.abs(value);
        double tiles = Math.floor(Math.abs(value) / TILE_WIDTH) + offset;
        return sign * tiles * TILE_WIDTH;
    }
}