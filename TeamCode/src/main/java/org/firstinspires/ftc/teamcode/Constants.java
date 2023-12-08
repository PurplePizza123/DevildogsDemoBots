package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;

import java.util.HashMap;

public class Constants {
    public static HashMap<Integer, Pose2d> targetPoses = new HashMap(){{
        put(1, new Pose2d(62, 41.5, Math.toRadians(0)));
        put(2, new Pose2d(62, 35.5, 0));
        put(3, new Pose2d(62, 29.5, 0));
        put(4, new Pose2d(62, -29.5, 0));
        put(5, new Pose2d(62, -35.5, 0));
        put(6, new Pose2d(62, -41.5, 0));
        put(7, new Pose2d(60, 32, 0));
        put(8, new Pose2d(60, 32, 0));
        put(9, new Pose2d(60, 32, 0));
        put(10, new Pose2d(60, 32, 0));
    }};
}
