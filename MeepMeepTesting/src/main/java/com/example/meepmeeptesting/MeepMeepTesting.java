package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.Game.Plan.A;
import static com.example.meepmeeptesting.Game.Plan.B;
import static com.example.meepmeeptesting.Game.Side.LEFT;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

public class MeepMeepTesting {
    private static final Game.Side side = LEFT;
    private static final Game.Plan plan = A;
    private static final int times = 2;
    private static final int detectionId = 0;

    private static TrajectorySequenceBuilder builder;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            .setDimensions(14.75, 14)
            .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(180), 12.82)
            .followTrajectorySequence(drive -> {
                    builder = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0));
                    execute();
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
        if(plan == A) scoreStack(times);
        if(plan == B) prepareToPark();
    }

    public static void prepareToPark() {
        turn(1, adapt(90));
    }

    public static void scoreStartCone() {
        move(0, 1, 4);
        move(adapt(1), 0, 26);
        move(0, 1, 24);
        turn(1, adapt(-45));
        move(0, 1, 11.5);
        move(0, -1, 11.5);
    }

    public static void scoreStack(int times) {
        turn(1, 0);
        move(0, 1, 24);
        turn(1, adapt(90));

        while (--times >= 0) {
            move(0, 1, 49.5);
            move(0, -1, 49.5);
            turn(1, adapt(-135));
            move(0, 1, 11.5);
            move(0, -1, 11.5);
            turn(1, adapt(90));
        }
    }

    public static void park() {
        boolean isLeft = side == LEFT;
        if (detectionId == 0) move(0, 1, isLeft ? 46 : 0);
        if (detectionId == 1) move(0, 1, 24);
        if (detectionId == 2) move(0, 1, isLeft ? 0 : 46);
    }

    public static double adapt(double value) {
        return side.sign * value;
    }

    public static void turn(double power, double heading) {
        builder.turn(Math.toRadians(power * heading));
    }

    public static void move(double strafe, double forward, double distance) {
        if (strafe != 0) builder.strafeRight(strafe * distance);
        if (forward != 0) builder.forward(forward * distance);
    }
}