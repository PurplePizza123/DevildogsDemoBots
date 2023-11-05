package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class HoistSubsystem extends HardwareSubsystem {
    public static double POWER = 1;

    public HoistSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData("Hoist", "%.2f vel", hardware.hoist.get());
    }

    public void up() {
        hardware.hoist.set(+POWER);
    }

    public void down() {
        hardware.hoist.set(-POWER);
    }

    public void stop() {
        hardware.hoist.set(0);
    }
}
