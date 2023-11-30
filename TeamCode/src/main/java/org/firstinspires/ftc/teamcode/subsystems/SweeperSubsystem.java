package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class SweeperSubsystem extends SubsystemBase {
    public static double POWER = 1;

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
        set(+POWER);
    }

    public void out() {
        set(-POWER);
    }

    public void stop() {
        set(0);
    }

    private void set(double power) {
        hardware.sweeperLeft.set(power);
        hardware.sweeperRight.set(power);
        hardware.sweeperCenter.set(power);
    }
}