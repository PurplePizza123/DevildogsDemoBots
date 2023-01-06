package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.Game.Alliance.BLUE;
import static com.example.meepmeeptesting.Game.Alliance.RED;
import static com.example.meepmeeptesting.Game.Plan.A;
import static com.example.meepmeeptesting.Game.Plan.B;
import static com.example.meepmeeptesting.Game.Side.LEFT;
import static com.example.meepmeeptesting.Game.Side.NORTH;
import static com.example.meepmeeptesting.Game.Side.SOUTH;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MeepMeepTesting {
    private static final double TILE_WIDTH = 23.5;
    private static final double ROBOT_WIDTH = 14.75;
    private static final double ROBOT_LENGTH = 14;
    private static final double TRACK_WIDTH = 12.82;
    private static final double INTAKE_OFFSET = -7;

    private static final Game.Alliance alliance = BLUE;
    private static final Game.Side side = NORTH;
    private static final Game.Plan plan = A;
    private static final int times = 2;
    private static final int detectionId = 0;

    private static MeepMeep meepMeep;
    private static Pose2d current;
    private static TrajectorySequenceBuilder builder;

    public static void main(String[] args) {
        meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            .setDimensions(ROBOT_WIDTH, ROBOT_LENGTH)
            .setConstraints(45, 45, Math.toRadians(200), Math.toRadians(200), TRACK_WIDTH)
            .setColorScheme(new ColorSchemeRedDark())
            .followTrajectorySequence(
                drive -> {
                    current = getStartPose(alliance, side);
                    builder = drive.trajectorySequenceBuilder(current);
                    execute();
                    builder.waitSeconds(1);
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

    public static void execute() {
        scoreStartCone();
        executeChosenPlan();
        park();
    }

    public static void executeChosenPlan() {
        if (plan == A) scoreStack(times);
        if (plan == B) prepareToPark();
    }

    public static void prepareToPark() {
//        return drive.turn(1, adapt(90));
    }

    public static void scoreStartCone() {
        Pose2d junction = getJunctionPose("W3");
        Pose2d[] poses = getTransitionPoses(current, junction, INTAKE_OFFSET, false);
        Arrays.stream(poses).forEach(builder::lineToLinearHeading);
    }

    public static void scoreStack(int times) {
//        int stackedCones = 5;
//
//        SequentialCommandGroup group = new SequentialCommandGroup(
//            drive.turn(1, 0),
//            drive.move(0, 1, 24).alongWith(lift.to(STACK)),
//            drive.turn(1, adapt(90))
//        );
//
//        while (--times >= 0) {
//            group.addCommands(
//                drive.move(0, 1, 49.5),
//                intake.getCone(--stackedCones).andThen(wait.seconds(0.3)),
//                drive.move(0, -1, 49.5).alongWith(lift.to(HIGH)),
//                drive.turn(1, adapt(-135)),
//                drive.move(0, 1, 11.5),
//                intake.setCone(stackedCones),
//                drive.turn(1, adapt(90))
//            );
//        }
//
//        return group;
    }

    public static void park() {
//        boolean isLeft = subsystems.menu.side == LEFT;
//        int detectionId = subsystems.vision.getDetectionId() + 1;
//        String detectionLabel = subsystems.vision.getDetectionLabel();
//        return new SelectCommand(
//            new HashMap<Object, Command>() {{
//                put(1, drive.move(0, 1, isLeft ? 46 : 0));
//                put(2, drive.move(0, 1, 24));
//                put(3, drive.move(0, 1, isLeft ? 0 : 46));
//            }}, () -> detectionLabel == "none" ? (isLeft ? 3 : 1) : detectionId
//        ).alongWith(lift.to(GROUND).andThen(wait.seconds(3)));
    }

    public static Pose2d getTilePose(String label) {
        return new Pose2d(
            +(label.charAt(1) - (('3' + '4') / 2d)) * TILE_WIDTH,
            -(label.charAt(0) - (('C' + 'D') / 2d)) * TILE_WIDTH
        );
    }

    public static Pose2d getJunctionPose(String label) {
        return new Pose2d(
            +(label.charAt(1) - '3') * TILE_WIDTH,
            -(label.charAt(0) - 'X') * TILE_WIDTH
        );
    }

    public static Pose2d getStartPose(Game.Alliance alliance, Game.Side side) {
        return new Pose2d(
            side.sign * 1.5 * TILE_WIDTH,
            alliance.sign * (3 * TILE_WIDTH - ROBOT_LENGTH / 2),
            alliance.sign * Math.toRadians(-90)
        );
    }

    public static Pose2d[] getTransitionPoses(Pose2d start, Pose2d end, double offset, boolean y1st) {
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

        double startHeading = start.getHeading();
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

        for (Pose2d nextPose : Arrays.asList(start, startTile, midTile, endTile)) {
            Pose2d lastPose = poses.get(poses.size() - 1);
            if (lastPose.getX() == nextPose.getX() && lastPose.getY() == nextPose.getY()) poses.remove(lastPose);
            poses.add(nextPose);
        }

        updatePoseHeadings(poses, startHeading, endHeading);

        poses.remove(start);

        return poses.toArray(new Pose2d[0]);
    }

    public static void updatePoseHeadings(ArrayList<Pose2d> poses, double startHeading, double endHeading) {
        ArrayList<Double> distances = new ArrayList<>();

        double totalDistance = 0;

        for (int i = 1; i < poses.size(); i++) {
            Pose2d poseA = poses.get(i - 1);
            Pose2d poseB = poses.get(i);

            distances.add(
                totalDistance += Math.hypot(
                    poseA.getX() - poseB.getX(),
                    poseA.getY() - poseB.getX()
                )
            );
        }

        double remainder = (endHeading - startHeading);
        if (remainder < -180) remainder += 360;
        if (remainder > +180) remainder -= 360;

        for (int i = 1; i < poses.size(); i++) {
            poses.set(i, new Pose2d(
                poses.get(i).getX(),
                poses.get(i).getY(),
                startHeading + remainder * distances.get(i - 1) / totalDistance
            ));
        }
    }

    private static double nearestTile(double value, double offset) {
        double sign = value == 0 ? 1 : value / Math.abs(value);
        double tiles = Math.floor(Math.abs(value) / TILE_WIDTH) + offset;
        return sign * tiles * TILE_WIDTH;
    }
}
