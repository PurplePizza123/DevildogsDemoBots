package org.firstinspires.ftc.teamcode.game;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Config {
    public static Config config = new Config();
    public transient boolean auto;
    public transient boolean started;
    public Pose2d pose = new Pose2d(0, 0, 0);
    public transient ElapsedTime timer = new ElapsedTime();
}
