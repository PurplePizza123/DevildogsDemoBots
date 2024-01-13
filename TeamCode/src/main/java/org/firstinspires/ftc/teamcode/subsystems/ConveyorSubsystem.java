package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.RawPower;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class ConveyorSubsystem extends SubsystemBase {
    public static double POWER = 1;

    public ConveyorSubsystem() {
        hardware.conveyor.setRunMode(RawPower);
        hardware.conveyor.setInverted(true);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Conveyor",
            () -> String.format(
                "%.1f pow",
                hardware.conveyor.get()
            )
        );
    }

    public void in() {
        hardware.conveyor.set(+POWER);
    }

    public void in(double power) {
        hardware.conveyor.set(power);
    }

    public void out() {
        hardware.conveyor.set(-POWER);
    }

    public void out(double power) {
        hardware.conveyor.set(power);
    }

    public void stop() {
        hardware.conveyor.set(0);
    }
}
