package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

@Config
public class ConveyorSubsystem extends SubsystemBase {
    public static double POWER = 0.5;

    private boolean pressed = false;

    public ConveyorSubsystem() {
        hardware.conveyor.motor.setMode(RUN_WITHOUT_ENCODER);
        hardware.conveyor.motor.setDirection(REVERSE);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        telemetry.addData(
            "Conveyor",
            () -> String.format(
                "%.1f pow, pressed: %s",
                hardware.conveyor.get(),
                pressed
            )
        );
    }

    public void in() {
        pressed = true;
        hardware.conveyor.set(+POWER);
    }

    public void out() {
        pressed = true;
        hardware.conveyor.set(-POWER);
    }

    public void stop() {
        pressed = false;
        hardware.conveyor.set(0);
    }
}
