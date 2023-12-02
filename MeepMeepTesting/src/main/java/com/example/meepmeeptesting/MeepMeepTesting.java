package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.Alliance.BLUE;
import static com.example.meepmeeptesting.Alliance.RED;
import static com.example.meepmeeptesting.Side.NORTH;
import static com.example.meepmeeptesting.Side.SOUTH;

import static java.lang.Double.NaN;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    private static final double TILE_WIDTH = 23.5;
    private static final double TILE_WIDTH_HALF = TILE_WIDTH / 2;
    private static final double ROBOT_WIDTH = 12;
    private static final double ROBOT_LENGTH = 12;
    private static final double TRACK_WIDTH = 12;
    private static final double INTAKE_OFFSET = -4.75;
    private static final double MAX_VEL = 30;
    private static final double MAX_VAL_ESTIMATE = ((Math.PI * 96 * 435) / 60) / 25.4;
    private static final double MAX_ACCEL = MAX_VEL;
    private static final double MAX_ANG_VEL = Math.toRadians(360);
    private static final double MAX_ANG_ACCEL = MAX_ANG_VEL;
    private static final double START_TO_START_TILE_MIN = 6;

    public static Alliance alliance = BLUE;
    public static Side side = SOUTH;
    public static int detection = 1;

    private static MeepMeep meepMeep;
    private static Pose2d current;
    private static TrajectoryActionBuilder builder;

    public static void main(String[] args) {
        meepMeep = new MeepMeep(600);

        meepMeep
            .setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
            .setDarkMode(true)
            .setBackgroundAlpha(0.20f)
            .addEntity(createBot(RED, NORTH))
            .addEntity(createBot(RED, SOUTH))
            .addEntity(createBot(BLUE, NORTH))
            .addEntity(createBot(BLUE, SOUTH))
            .start();
    }

    private static RoadRunnerBotEntity createBot(Alliance alliance, Side side) {
        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
            .setDimensions(ROBOT_WIDTH, ROBOT_LENGTH)
            .setColorScheme(alliance == BLUE ? new ColorSchemeBlueDark() : new ColorSchemeRedDark())
            .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
            .build();

        current = getStartPose(
            MeepMeepTesting.alliance = alliance,
            MeepMeepTesting.side = side
        );

        builder = bot.getDrive().actionBuilder(current);

        execute();

        bot.runAction(
            builder.build()
        );

        return bot;
    }

    public static void execute() {
        scorePurplePixel();
        scoreYellowPixel();
        scoreStack(1);
        park();
    }

    public static void scorePurplePixel() {
        toPose(
            getSpikeMarkPose()
        );

        toPoseStrafe(
            getSpikeMarkTilePose(), false
        );
    }
    public static void scoreYellowPixel() {
        toPoseStrafe(getBackdropPose(), false);
    }

    public static void scoreStack(int times) {
        while(times-- > 0) {
            toPoseStrafe(getStackPose(), true);
            toPoseStrafe(getBackdropPose(), false);
        }
    }

    public static void park() {
        toPoseStrafe(getParkPose(), false);
    }

    public static Pose2d getStackPose() {
        return createPose(
            -3 * TILE_WIDTH,
            alliance.sign * 1.5 * TILE_WIDTH,
            Math.toRadians(180),
            Axial.FRONT
        ); // TODO: based on driver 2 sticks change out of the 6 different stacks by going side to side.
        // change y to change by alliance 23.5
    }

    public static Pose2d getSpikeMarkPose() {
        return createPose(
            ((side == NORTH ? 0.5 : -1.5) * TILE_WIDTH) - alliance.sign * detection * TILE_WIDTH_HALF,
            alliance.sign * (detection == 0 ? 1 : 1.1) * TILE_WIDTH,
            Math.toRadians(alliance.sign * -90 - 45 * detection),
            Axial.FRONT
        );
    }

    public static void toPose(Pose2d pose) {
        toPose(pose, false);
    }

    public static void toPose(Pose2d pose, boolean reverse) {
        builder = builder.setReversed(reverse);
        builder = Math.abs(normalizeHeading(pose.heading.log() - current.heading.log())) <= 0.01d ?
            builder.strafeTo(pose.position) :
            builder.splineTo(pose.position, pose.heading);
        current = pose;
    }

    public static void toPoseStrafe(Pose2d pose, boolean reverse) {
        builder = builder.setReversed(reverse);
        builder = builder.strafeToLinearHeading(pose.position, pose.heading);
        current = pose;
    }

    public static Pose2d getStartPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * (side == NORTH ? 0.5 : 1.5) * TILE_WIDTH,
            alliance.sign * (3 * TILE_WIDTH - ROBOT_LENGTH / 2),
            alliance.sign * Math.toRadians(-90)
        );
    }

    public static Pose2d getSpikeMarkApproachPose(Alliance alliance, Side side, int detection) {
        return createPose(
            ((side == NORTH ? 0.5 : 1.5) * side.sign * TILE_WIDTH) - alliance.sign * detection * TILE_WIDTH_HALF,
            alliance.sign * (TILE_WIDTH + ROBOT_LENGTH * (detection == 0 ? 0 : 0.25)),
            Math.toRadians(alliance.sign * -90 + -45 * detection),
            Axial.FRONT
        );
    }

    public static Pose2d getSpikeMarkTilePose() {
        return createPose(
            (side == NORTH ? 0.5 : -1.5) * TILE_WIDTH,
            alliance.sign * 1.5 * TILE_WIDTH,
            Math.toRadians(0),
            Axial.CENTER
        );
    }

    public static Pose2d getBackdropPose() {
        return createPose(
            TILE_WIDTH * 2.5,
            (alliance.sign * 1.5 - 0.25 * detection) * TILE_WIDTH,
            Math.toRadians(0), Axial.FRONT
        );
    }

    public static Pose2d getParkPose() {
        return createPose(
            TILE_WIDTH * 2.5,
            alliance.sign * (side == NORTH ? 2.5 : 0.5) * TILE_WIDTH,
            Math.toRadians(0), Axial.FRONT
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

    public static Pose2d createPose(Pose2d pose, Axial axial) {
        return createPose(pose, axial, Lateral.CENTER);
    }

    public static Pose2d createPose(Pose2d pose, Lateral lateral) {
        return createPose(pose, Axial.CENTER, lateral);
    }

    public static Pose2d createPose(Pose2d pose, Axial axial, Lateral lateral) {
        return createPose(pose.position.x, pose.position.y, pose.heading.log(), axial, lateral);
    }

    public static Pose2d createPose(double x, double y, double heading) {
        return createPose(x, y, heading, Axial.CENTER, Lateral.CENTER);
    }

    public static Pose2d createPose(double x, double y, double heading, Axial axial) {
        return createPose(x, y, heading, axial, Lateral.CENTER);
    }

    public static Pose2d createPose(double x, double y, double heading, Lateral lateral) {
        return createPose(x, y, heading, Axial.CENTER, lateral);
    }

    public static Pose2d createPose(double x, double y, double heading, Axial axial, Lateral lateral) {
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

    private static double normalizeHeading(double heading) {
        if (heading > +Math.PI) heading -= Math.PI * 2;
        if (heading < -Math.PI) heading += Math.PI * 2;
        return heading;
    }
}