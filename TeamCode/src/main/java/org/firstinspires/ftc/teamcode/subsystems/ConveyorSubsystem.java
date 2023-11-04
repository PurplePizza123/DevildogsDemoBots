package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class ConveyorSubsystem extends HardwareSubsystem {
    public static double POWER = 1;

    public ConveyorSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData("Conveyor", "%.2f vel", hardware.conveyor.get());
    }

    public void in() {
        hardware.conveyor.set(+POWER);
    }

    public void out() {
        hardware.conveyor.set(-POWER);
    }

    public void stop() {
        hardware.conveyor.set(0);
    }
}
