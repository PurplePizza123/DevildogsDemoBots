package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.Alliance.BLUE;
import static com.example.meepmeeptesting.Alliance.RED;
import static com.example.meepmeeptesting.Side.NORTH;
import static com.example.meepmeeptesting.Side.SOUTH;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
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
    private static final double MAX_VEL = 45;
    private static final double MAX_ACCEL = 45;
    private static final double MAX_ANG_VEL = Math.toRadians(200);
    private static final double MAX_ANG_ACCEL = Math.toRadians(200);

    public static Alliance alliance = BLUE;
    public static Side side = SOUTH;
    public static int stacks = 5;
    public static int detectionId = 0;

    private static MeepMeep meepMeep;
    private static Pose2d current;
    private static TrajectorySequenceBuilder builder;

    public static void main(String[] args) {
        meepMeep = new MeepMeep(600);

        meepMeep
            .setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
            .setDarkMode(true)
            .setBackgroundAlpha(0.95f)
            .addEntity(createBot(RED, NORTH))
            .addEntity(createBot(RED, SOUTH))
            .addEntity(createBot(BLUE, NORTH))
            .addEntity(createBot(BLUE, SOUTH))
            .start();
    }

    private static RoadRunnerBotEntity createBot(Alliance alliance, Side side) {
        return new DefaultBotBuilder(meepMeep)
            .setDimensions(ROBOT_WIDTH, ROBOT_LENGTH)
            .setColorScheme(alliance == BLUE ? new ColorSchemeBlueDark() : new ColorSchemeRedDark())
            .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
            .followTrajectorySequence(drive -> {
                current = getStartPose(MeepMeepTesting.alliance = alliance, MeepMeepTesting.side = side);
                builder = drive.trajectorySequenceBuilder(current);
                execute();
                return builder.build();
            });
    }

    public static void execute() {
        scoreStartCone();
        scoreStack(stacks);
        park();
    }

    public static void scoreStartCone() {
        String outerColumn = String.valueOf((char)('X' - alliance.sign * 2));
        int outerRow = 3 + side.sign * 2;
        toJunction(outerColumn + outerRow);
    }

    public static void scoreStack(int times) {
        String outerColumn = String.valueOf((char)('X' - alliance.sign * 2));
        String innerColumn = String.valueOf((char)('X' - alliance.sign));
        int outerRow = 3 + side.sign * 2;
        int innerRow = 3 + side.sign;

        String[] junctions = {
            outerColumn + innerRow,
            innerColumn + outerRow,
            innerColumn + outerRow,
            innerColumn + outerRow,
            innerColumn + innerRow
        };

        while (times > 0) {
            toStack(alliance, side);
            toJunction(junctions[5 - times--]);
        }
    }

    public static void park() {
        String column = alliance == BLUE ? "C" : "D";
        int start = side == SOUTH ? 1 : 4;
        if (alliance == BLUE) start += 2;
        int row = start - alliance.sign * detectionId;
        toTile(column + row);
    }

    public static void toTile(String label) {
        toPose(
            getTilePose(label), 0, true
        );
    }

    public static void toJunction(String label) {
        toPose(
            getJunctionPose(label), INTAKE_OFFSET, true
        );
    }

    public static void toStack(Alliance alliance, Side side) {
        toPose(
            getStackPose(alliance, side), INTAKE_OFFSET, true
        );
    }

    public static void toSubstation(Alliance alliance, Side side) {
        toPose(
            getSubstationPose(alliance, side), INTAKE_OFFSET, true
        );
    }

    public static void toTerminal(Alliance alliance, Side side) {
        toPose(
            getTerminalPose(alliance, side), INTAKE_OFFSET, true
        );
    }

    public static void toPose(Pose2d pose, double offset, boolean y1st) {
        Pose2d[] poses = getTransitionPoses(current, pose, offset, y1st);
        Arrays.stream(poses).forEach(builder::lineToLinearHeading);
        current = poses[poses.length - 1];
    }

    public static Pose2d getStartPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * 1.5 * TILE_WIDTH,
            alliance.sign * (3 * TILE_WIDTH - ROBOT_LENGTH / 2),
            alliance.sign * Math.toRadians(-90)
        );
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

    public static Pose2d getStackPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * (3 * TILE_WIDTH - 4),
            alliance.sign * 0.5 * TILE_WIDTH
        );
    }

    public static Pose2d getSubstationPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * 0.25 * TILE_WIDTH,
            alliance.sign * 2.75 * TILE_WIDTH
        );
    }

    public static Pose2d getTerminalPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * 2.75 * TILE_WIDTH,
            alliance.sign * -side.sign * 2.75 * TILE_WIDTH
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

        for (Pose2d nextPose : Arrays.asList(start, startTile, midTile, endTile, end)) {
            Pose2d lastPose = poses.get(poses.size() - 1);
            if (lastPose.getX() == nextPose.getX() &&
                lastPose.getY() == nextPose.getY())
                    poses.remove(lastPose);
            poses.add(nextPose);
        }

        updatePoseHeadings(poses);

        poses.remove(start);

        return poses.toArray(new Pose2d[0]);
    }

    private static void updatePoseHeadings(ArrayList<Pose2d> poses) {
        Pose2d start = poses.get(0);
        Pose2d end = poses.get(poses.size() - 1);

        ArrayList<Double> distances = new ArrayList<>();

        double totalDistance = 0;

        for (int i = 1; i < poses.size(); i++) {
            Pose2d poseA = poses.get(i - 1);
            Pose2d poseB = poses.get(i);

            distances.add(
                totalDistance += Math.hypot(
                    poseA.getX() - poseB.getX(),
                    poseA.getY() - poseB.getY()
                )
            );
        }

        double remainder = end.getHeading() - start.getHeading();
        if (remainder > +Math.PI) remainder -= Math.PI * 2;
        if (remainder < -Math.PI) remainder += Math.PI * 2;

        for (int i = 1; i < poses.size(); i++) {
            poses.set(i, new Pose2d(
                poses.get(i).getX(),
                poses.get(i).getY(),
                start.getHeading() + remainder * distances.get(i - 1) / totalDistance
            ));
        }
    }

    private static double nearestTile(double value, double offset) {
        double sign = value == 0 ? 1 : value / Math.abs(value);
        double tiles = Math.floor(Math.abs(value) / TILE_WIDTH) + offset;
        return sign * tiles * TILE_WIDTH;
    }
}
