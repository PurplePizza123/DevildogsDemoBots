package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class SweeperSubsystem extends HardwareSubsystem {
    public static double POWER = 1;

    public SweeperSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData(
            "Sweeper (L,R,C)", "%.2f vel, %.2f vel, %.2f vel",
            hardware.sweeperCenter.get(),
            hardware.sweeperLeft.get(),
            hardware.sweeperRight.get()
        );
    }

    public void in() {
        hardware.sweeperLeft.set(+POWER);
        hardware.sweeperRight.set(+POWER);
        hardware.sweeperCenter.set(+POWER);
    }

    public void out() {
        hardware.sweeperLeft.set(-POWER);
        hardware.sweeperRight.set(-POWER);
        hardware.sweeperCenter.set(-POWER);
    }

    public void stop() {
        hardware.sweeperLeft.set(0);
        hardware.sweeperRight.set(0);
        hardware.sweeperCenter.set(0);
    }
}
