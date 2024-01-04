package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class SweeperSubsystem extends SubsystemBase {
    public static double POWER = 1;

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Sweeper (L,R,C)",
                () -> String.format(
                "%.2f vel, %.2f vel, %.2f vel",
                hardware.sweeperCenter.getPower(),
                hardware.sweeperLeft.getPower(),
                hardware.sweeperRight.getPower()
            )
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
        hardware.sweeperLeft.setPower(power);
        hardware.sweeperRight.setPower(power);
        hardware.sweeperCenter.setPower(power);
    }
}
