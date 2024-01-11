package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLACK;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLUE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.GREEN;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.ORANGE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.RED;
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
        RevBlinkinLedDriver.BlinkinPattern pattern = BLACK;

        if (!config.started)
            if (config.alliance == Alliance.RED && config.side == Side.NORTH) pattern = RED;
            else if (config.alliance == Alliance.RED && config.side == Side.SOUTH) pattern = HEARTBEAT_RED;
            else if (config.alliance == Alliance.BLUE && config.side == Side.NORTH) pattern = BLUE;
            else if (config.alliance == Alliance.BLUE && config.side == Side.SOUTH) pattern = HEARTBEAT_BLUE;
            else pattern = RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD;

        if (vision.detectionPose != null) {
            pattern = GREEN;
        }

        if (!config.auto && config.started && config.timer.seconds() >= 80)
            pattern = ORANGE;

        hardware.lights.setPattern(pattern);
    }
}
