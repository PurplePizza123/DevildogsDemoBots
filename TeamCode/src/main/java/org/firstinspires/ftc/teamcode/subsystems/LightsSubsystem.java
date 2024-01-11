package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BREATH_BLUE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BREATH_RED;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.GREEN;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_BLUE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_RED;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.SHOT_RED;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.STROBE_RED;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.vision;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

public class LightsSubsystem extends SubsystemBase {
    @Override
    public void periodic() {
        RevBlinkinLedDriver.BlinkinPattern pattern;

        if (!config.started)
            if (config.alliance == Alliance.RED && config.side == Side.NORTH) pattern = HEARTBEAT_RED;
            else if (config.alliance == Alliance.RED && config.side == Side.SOUTH) pattern = LIGHT_CHASE_RED;
            else if (config.alliance == Alliance.BLUE && config.side == Side.NORTH) pattern = HEARTBEAT_BLUE;
            else if (config.alliance == Alliance.BLUE && config.side == Side.SOUTH) pattern = LIGHT_CHASE_BLUE;
            else pattern = STROBE_GOLD;
        else
            pattern = config.alliance == Alliance.RED ? BREATH_RED : BREATH_BLUE;

        if (vision.detectionPose != null)
            pattern = GREEN;

        if (!config.auto && config.started && config.timer.seconds() >= 80)
            pattern = STROBE_RED;

        hardware.lights.setPattern(pattern);
    }
}
