package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class LiftSubsystem extends HardwareSubsystem {
    public static double LIFT_SPOOL_CIRCUMFERENCE = 4.409;
    public static double LIFT_PULSES_PER_REVOLUTION = 384.5;
    public static double LIFT_HEIGHT_PER_PULSE = LIFT_SPOOL_CIRCUMFERENCE / LIFT_PULSES_PER_REVOLUTION;
    public static double POWER_UP = 1.0;
    public static double POWER_DOWN = 0.5;
    public static double MIN = 2.25;
    public static double MAX = 34.5; // TODO: Change back to 37.5 when servo wires are lengthened.
    public static double INCREMENT = 0.5;
    private static double HEIGHT = MIN;

    public LiftSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        hardware.lift.setMode(STOP_AND_RESET_ENCODER);
        hardware.lift.setTargetPosition(0);
        hardware.lift.setMode(RUN_TO_POSITION);
        hardware.lift.setPower(POWER_UP);
    }

    @Override
    public void periodic() {
        telemetry.addData("Lift","%.2f pow, %.2f height", hardware.lift.getPower(), hardware.lift.getCurrentPosition() * LIFT_HEIGHT_PER_PULSE);
        telemetry.update();
    }

    public enum LiftPosition {
        GROUND(MIN), LOW(17), MID(27), HIGH(37), INTAKE(7);

        public double height;

        LiftPosition(double height) {
            this.height = height;
        }
    }

    public void up() {
        to(HEIGHT + INCREMENT);
    }

    public void down() {
        to(HEIGHT - INCREMENT);
    }

    public void to(LiftPosition height) {
        to(height.height);
    }

    public void to(double height) {
        height = clamp(height, MIN, MAX);
        hardware.lift.setPower(height > HEIGHT ? POWER_UP : POWER_DOWN);
        hardware.lift.setTargetPosition(
            (int)(((HEIGHT = height) - MIN) / LIFT_HEIGHT_PER_PULSE)
        );
    }
}
