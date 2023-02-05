package org.firstinspires.ftc.teamcode.game;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class Config {
    public transient static Config config = new Config();
    public transient boolean auto;
    public Alliance alliance;
    public Side side;
    public int stacks = 2;
    public double delay = 0;
    public String junction = "X3";
    public Pose2d pose = new Pose2d();
}
