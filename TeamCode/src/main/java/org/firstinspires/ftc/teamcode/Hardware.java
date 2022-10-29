package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    public MotorEx driveLeftFront;
    public MotorEx driveRightFront;
    public MotorEx driveLeftRear;
    public MotorEx driveRightRear;
    public ServoEx intake;
    public MotorEx lift;


    public Hardware(HardwareMap hardwareMap) {
        driveLeftFront = new MotorEx(hardwareMap, "driveLeftFront", Motor.GoBILDA.RPM_312);
        driveRightFront = new MotorEx(hardwareMap, "driveRightFront", Motor.GoBILDA.RPM_312);
        driveLeftRear = new MotorEx(hardwareMap, "driveLeftRear", Motor.GoBILDA.RPM_312);
        driveRightRear = new MotorEx(hardwareMap, "driveRightRear", Motor.GoBILDA.RPM_312);
        intake = new SimpleServo(hardwareMap, "driveRightRear",-1,-1);
        lift = new MotorEx(hardwareMap, "lift", Motor.GoBILDA.RPM_435);
    }
}