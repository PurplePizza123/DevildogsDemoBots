package org.firstinspires.ftc.teamcode.game;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLACK;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Config {
    public transient static Config config;
    public transient boolean auto;
    public transient boolean started;
    public Alliance alliance;
    public Side side;
    public int stacks = 1;
    public double delay = 0;
    public String junction = "X3";
    public Pose2d pose = new Pose2d();
    public transient RevBlinkinLedDriver.BlinkinPattern lightingDefault = BLACK;
    public transient RevBlinkinLedDriver.BlinkinPattern lightingCurrent = BLACK;
    public transient ElapsedTime timer = new ElapsedTime();
    public transient int navSamples;
}
