package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.Alliance.BLUE;
import static com.example.meepmeeptesting.Alliance.RED;
import static com.example.meepmeeptesting.Side.NORTH;
import static com.example.meepmeeptesting.Side.SOUTH;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public class MeepMeepTesting {
    private static final double TILE_WIDTH = 23.5;
    private static final double TILE_WIDTH_HALF = TILE_WIDTH / 2;
    private static final double ROBOT_WIDTH = 15.625;
    private static final double ROBOT_LENGTH = 15.25;
    private static final double TRACK_WIDTH = 12.82;
    private static final double INTAKE_OFFSET = -4.75;
    private static final double MAX_VEL = 35;
    private static final double MAX_ACCEL = MAX_VEL;
    private static final double MAX_ANG_VEL = Math.toRadians(200);
    private static final double MAX_ANG_ACCEL = MAX_ANG_VEL;
    private static final double START_TO_START_TILE_MIN = 6;

    public static Alliance alliance = BLUE;
    public static Side side = SOUTH;
    public static int stacks = 2;
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
        moveSignalCone();
        scoreStack(stacks);
        park();
    }

    public static void scoreStartCone() {
        String column = String.valueOf((char)('X' - alliance.sign * 2));
        int row = 3 + side.sign;
        toJunction(column + row);
        setCone();
    }

    public static void moveSignalCone() {
        String column = alliance == BLUE ? "C" : "D";
        int row = side == NORTH ? 5 : 2;
        toTile(column + row, o -> o.endTileY = 8);
    }

    public static void scoreStack(int times) {
        String column = String.valueOf((char)('X' - alliance.sign));
        int row = 3 + side.sign * 2;

        toStack(alliance, side);
        getCone();

        while (--times >= 0) {
            if (times == 0) {
                toJunctionAuto(column + (row - side.sign));
                setCone();
            } else {
                toJunctionAuto(column + row);
                setCone();
            }

            if (times > 0) {
                toStackAuto(alliance, side);
                getCone();
            }
        }
    }

    public static void park() {
        String column = alliance == BLUE ? "C" : "D";
        int start = side == SOUTH ? 1 : 4;
        if (alliance == BLUE) start += 2;
        int row = start - alliance.sign * detectionId;
        toTile(column + row);
    }

    public static void forward(double distance) {
        builder.waitSeconds(0.25);
        builder.forward(distance);
        builder.waitSeconds(0.25);
    }

    public static void getCone() {
        builder.waitSeconds(0.33);
    }

    public static void setCone() {
        builder.waitSeconds(0.33);
    }

    public static void toTile(String label) {
        toPose(
            getTilePose(label)
        );
    }

    public static void toTile(String label, Consumer<Offsets>... consumers) {
        toPose(
            getTilePose(label),
            consumers
        );
    }

    public static void toJunction(String label) {
        toPose(
            getJunctionPose(label),
            o -> o.endX = o.endY = INTAKE_OFFSET
        );
    }

    public static void toJunctionAuto(String label) {
        toPose(
            getJunctionPose(label),
            o -> o.startTileX = -4,
            o -> o.endX = o.endY = INTAKE_OFFSET
        );
    }

    public static void toStack(Alliance alliance, Side side) {
        toPose(
            getStackPose(alliance, side),
            o -> o.endTileX = +5.25,
            o -> o.endX = o.endY = INTAKE_OFFSET
        );
    }

    public static void toStackAuto(Alliance alliance, Side side) {
        toPose(
            getStackPose(alliance, side),
            o -> o.startTileX = -4,
            o -> o.endX = o.endY = INTAKE_OFFSET
        );
    }

    public static void toSubstation(Alliance alliance, Side side) {
        toPose(
            getSubstationPose(alliance, side),
            o -> o.endX = o.endY = INTAKE_OFFSET
        );
    }

    public static void toTerminal(Alliance alliance, Side side) {
        toPose(
            getTerminalPose(alliance, side),
            o -> o.endX = o.endY = INTAKE_OFFSET
        );
    }

    public static void toPose(Pose2d pose, Consumer<Offsets>... offsets) {
        Pose2d[] poses = getTransitionPoses(current, pose, offsets);

        for (int i = 1; i < poses.length; i++) {
            Pose2d prev = poses[i - 1];
            Pose2d curr = poses[i];

            double distance = Math.hypot(
                prev.getX() - curr.getX(),
                prev.getY() - curr.getY()
            );

            double remainder = curr.getHeading() - prev.getHeading();
            if (remainder > +Math.PI) remainder -= Math.PI * 2;
            if (remainder < -Math.PI) remainder += Math.PI * 2;

            if (remainder > +Math.PI * 0.9 || remainder < -Math.PI * 0.9) {
                curr = poses[i] = new Pose2d(curr.getX(), curr.getY(), prev.getHeading());
                builder.lineToConstantHeading(new Vector2d(curr.getX(), curr.getY()));
            } else {
                builder.turn(remainder);
                builder.lineToLinearHeading(curr);
            }

            current = curr;
        }
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
            side.sign * (3 * TILE_WIDTH - 1.75),
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

    public static Pose2d[] getTransitionPoses(Pose2d start, Pose2d end, Consumer<Offsets>... consumers) {
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
            nearestTile(start.getX(), TILE_WIDTH_HALF + offsets.startTileX),
            nearestTile(start.getY(), TILE_WIDTH_HALF + offsets.startTileY)
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
