package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

@Config
public class NavSubsystem extends SubsystemBase {
    private static final double TILE_WIDTH = 23.5;
    private static final double TILE_WIDTH_HALF = TILE_WIDTH / 2;
    private static final double ROBOT_LENGTH = 12;
    private static final double ROBOT_WIDTH = 12;

    public Pose2d getStartPose(Alliance alliance, Side side) {
        return new Pose2d(
            side.sign * (1.5 * TILE_WIDTH - config.offsetX),
            alliance.sign * (3 * TILE_WIDTH - ROBOT_LENGTH / 2 - config.offsetY),
            alliance.sign * Math.toRadians(-90)
        );
    }
}
