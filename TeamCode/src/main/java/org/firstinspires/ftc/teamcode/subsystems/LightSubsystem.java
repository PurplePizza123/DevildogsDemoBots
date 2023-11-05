package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.BLACK;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.RED;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class LightSubsystem extends SubsystemBase {
    @Override
    public void periodic() {
        if (config.started && config.timer.seconds() >= 80)
            config.lightingDefault = RED;

        hardware.lights.setPattern(
            config.lightingCurrent == BLACK ?
                config.lightingDefault :
                config.lightingCurrent
        );
    }
}
