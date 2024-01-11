package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLACK;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.GREEN;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.ORANGE;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.vision;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

public class LightsSubsystem extends SubsystemBase {
    @Override
    public void periodic() {
        RevBlinkinLedDriver.BlinkinPattern pattern = BLACK;

        if (vision.detectionPose != null) {
            pattern = GREEN;
        }

        if (!config.auto && config.started && config.timer.seconds() >= 80)
            pattern = ORANGE;

        hardware.lights.setPattern(pattern);
    }
}
