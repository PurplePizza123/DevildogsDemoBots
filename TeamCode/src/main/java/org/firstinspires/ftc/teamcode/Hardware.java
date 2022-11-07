package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware {
    public RevIMU imu;

    public MotorGroup drive;
    public MotorEx driveLeftFront;
    public MotorEx driveRightFront;
    public MotorEx driveLeftRear;
    public MotorEx driveRightRear;

    public MotorGroup intake;

    public DcMotor lift;

    public Hardware(HardwareMap hardwareMap) {
        imu = new RevIMU(hardwareMap);

        drive = new MotorGroup(
            driveLeftFront = new MotorEx(hardwareMap, "driveLeftFront", Motor.GoBILDA.RPM_312),
            driveRightFront = new MotorEx(hardwareMap, "driveRightFront", Motor.GoBILDA.RPM_312),
            driveLeftRear = new MotorEx(hardwareMap, "driveLeftRear", Motor.GoBILDA.RPM_312),
            driveRightRear = new MotorEx(hardwareMap, "driveRightRear", Motor.GoBILDA.RPM_312)
        );

        intake = new MotorGroup(
            new CRServo(hardwareMap, "intakeFront"),
            new CRServo(hardwareMap, "intakeLeft"),
            new CRServo(hardwareMap, "intakeRight")
        );

        lift = hardwareMap.get(DcMotor.class, "lift");
    }
}
