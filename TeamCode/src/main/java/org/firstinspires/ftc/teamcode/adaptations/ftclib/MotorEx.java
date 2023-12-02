package org.firstinspires.ftc.teamcode.adaptations.ftclib;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")
public class MotorEx extends com.arcrobotics.ftclib.hardware.motors.MotorEx {
    public MotorEx(@NonNull HardwareMap hMap, String id) {
        super(hMap, id);
    }

    public MotorEx(@NonNull HardwareMap hMap, String id, @NonNull GoBILDA gobildaType) {
        super(hMap, id, gobildaType);
    }

    public MotorEx(@NonNull HardwareMap hMap, String id, double cpr, double rpm) {
        super(hMap, id, cpr, rpm);
    }

    @Override
    public void set(double output) {
        if (runmode == RunMode.VelocityControl) {
            double speed = bufferFraction * output * ACHIEVABLE_MAX_TICKS_PER_SECOND;
            double velocity = veloController.calculate(getCorrectedVelocity(), speed) + feedforward.calculate(speed, getAcceleration());
            motorEx.setPower(velocity / ACHIEVABLE_MAX_TICKS_PER_SECOND);
        } else if (runmode == RunMode.PositionControl) {
            double error = positionController.calculate(encoder.getDistance());
            motorEx.setPower(output * error);
        } else {
            motorEx.setPower(output);
        }
    }
}
