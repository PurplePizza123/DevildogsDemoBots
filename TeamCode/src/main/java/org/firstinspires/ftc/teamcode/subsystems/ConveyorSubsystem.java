package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class ConveyorSubsystem extends SubsystemBase {
    public static double POWER = 1;

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
