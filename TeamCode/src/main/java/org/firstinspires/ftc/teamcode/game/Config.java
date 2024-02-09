package org.firstinspires.ftc.teamcode.game;

import static org.firstinspires.ftc.teamcode.game.Backdrop.LEFT;
import static org.firstinspires.ftc.teamcode.game.Parking.INNER;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Config {
    public static Config config;
    public transient boolean auto;
    public transient boolean started;
    public Alliance alliance = Alliance.UNKNOWN;
    public Side side = Side.UNKNOWN;
    public Parking parking = INNER;
    public Backdrop backdrop = LEFT;
    public int backdropRow = 1;
    public int backdropCol = 1;
    public int stackTimes = 0;
    public double offsetX = 0;
    public double offsetY = 0;
    public double delay = 0;
    public double pixelHeight = 3.3125;
    public Pose2d pose = new Pose2d(0, 0, 0);
    public transient ElapsedTime timer = new ElapsedTime();
}
