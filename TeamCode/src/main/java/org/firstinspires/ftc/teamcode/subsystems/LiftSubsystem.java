package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.PositionControl;
import static com.arcrobotics.ftclib.util.MathUtils.clamp;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class LiftSubsystem extends HardwareSubsystem {
    public static double LIFT_SPOOL_CIRCUMFERENCE = 4.409;
    public static double LIFT_PULSES_PER_REVOLUTION = 537.7;
    public static double LIFT_HEIGHT_PER_PULSE = LIFT_SPOOL_CIRCUMFERENCE / LIFT_PULSES_PER_REVOLUTION;
    public static double POWER_UP = 1.0;
    public static double POWER_DOWN = 1.0;
    public static double MIN = 2.25;
    public static double MAX = 36;
    public static double INCREMENT = 0.5;
    private static double HEIGHT = MIN;
    public boolean calibrated = false;


    public LiftSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        hardware.lift.setMode(RUN_USING_ENCODER);
    }

    @Override
    public void periodic() {
        telemetry.addData("Lift","%.2f pow, %.2f height, %b left, %b right", hardware.lift.getPower(), hardware.lift.getCurrentPosition() * LIFT_HEIGHT_PER_PULSE, !hardware.liftLeftLimit.getState(), !hardware.liftRightLimit.getState());

        if (calibrated) return;

        boolean isDown = !hardware.liftLeftLimit.getState() || !hardware.liftRightLimit.getState();

        if (isDown) {
            hardware.lift.setMode(STOP_AND_RESET_ENCODER);
            hardware.lift.setTargetPosition(0);
            hardware.lift.setMode(RUN_TO_POSITION);
            hardware.lift.setPower(POWER_UP);
            calibrated = true;
        }

    }

    public enum LiftPosition {
        GROUND(MIN), LOW(17), MID(27), HIGH(MAX), INTAKE(7), STACK(11.5);

        public double height;

        LiftPosition(double height) {
            this.height = height;
        }
    }

    public void calibrate() {
        calibrated = false;
        hardware.lift.setMode(RUN_USING_ENCODER);
        hardware.lift.setPower(-POWER_DOWN);
    }

    public void up() {
        to(HEIGHT + INCREMENT);
    }

    public void down() {
        to(HEIGHT - INCREMENT);
    }

    public void to(LiftPosition height, double offset) {
        to(height.height + offset);
    }

    public void to(double height) {
        height = clamp(height, -MAX, MAX);
        hardware.lift.setPower(height > HEIGHT ? POWER_UP : POWER_DOWN);
        hardware.lift.setTargetPosition(
            (int)(((HEIGHT = height) - MIN) / LIFT_HEIGHT_PER_PULSE)
        );
    }
}
