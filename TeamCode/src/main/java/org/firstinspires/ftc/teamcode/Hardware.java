package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware {
    public RevIMU imu;

    public DcMotor driveLeftFront;
    public DcMotor driveRightFront;
    public DcMotor driveLeftRear;
    public DcMotor driveRightRear;

    public MotorGroup intake;

    public DcMotor lift;

    public Hardware(HardwareMap hardwareMap) {
        imu = new RevIMU(hardwareMap);

        driveLeftFront = hardwareMap.get(DcMotor.class, "driveLeftFront");
        driveRightFront = hardwareMap.get(DcMotor.class, "driveRightFront");
        driveLeftRear = hardwareMap.get(DcMotor.class, "driveLeftRear");
        driveRightRear = hardwareMap.get(DcMotor.class, "driveRightRear");

        intake = new MotorGroup(
            new CRServo(hardwareMap, "intakeFront"),
            new CRServo(hardwareMap, "intakeLeft"),
            new CRServo(hardwareMap, "intakeRight")
        );

        lift = hardwareMap.get(DcMotor.class, "lift");
    }
}
