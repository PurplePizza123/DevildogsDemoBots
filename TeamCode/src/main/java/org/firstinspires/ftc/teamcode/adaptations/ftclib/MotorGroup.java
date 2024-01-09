package org.firstinspires.ftc.teamcode.adaptations.ftclib;

import androidx.annotation.NonNull;

public class MotorGroup extends com.arcrobotics.ftclib.hardware.motors.MotorGroup {
    public MotorGroup(@NonNull MotorEx leader, MotorEx... followers) {
        super(leader, followers);
    }

    public void setDistancePerRevolution(double distance) {
        iterator().forEachRemaining(
            motor -> ((MotorEx)motor).setDistancePerRevolution(distance)
        );
    }
}
