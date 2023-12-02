package org.firstinspires.ftc.teamcode.game;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLACK;

import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Config {
    public transient static Config config;
    public transient boolean auto;
    public transient boolean started;
    public Alliance alliance = RED;
    public Side side = NORTH;
    public double offsetX = 0;
    public double offsetY = 0;
    public double delay = 0;
    public int stacks = 1;
    public int detection = 0;
    public Pose2d pose = new Pose2d(0, 0, 0);
    public transient RevBlinkinLedDriver.BlinkinPattern lightingDefault = BLACK;
    public transient RevBlinkinLedDriver.BlinkinPattern lightingCurrent = BLACK;
    public transient ElapsedTime timer = new ElapsedTime();
    public transient int navSamples;
}
