package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

public class Hardware {
    public RevIMU imu;

    public DcMotor driveLeftFront;
    public DcMotor driveRightFront;
    public DcMotor driveLeftRear;
    public DcMotor driveRightRear;

    public MotorGroup intake;

    public DcMotor lift;

    public DigitalChannel liftLeftLimit;
    public DigitalChannel liftRightLimit;

    public OpenCvWebcam webcam;

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

        liftLeftLimit = hardwareMap.get(DigitalChannel.class, "liftLeftLimit");
        liftRightLimit = hardwareMap.get(DigitalChannel.class, "liftRightLimit");

        webcam = OpenCvCameraFactory.getInstance().createWebcam(
            hardwareMap.get(WebcamName.class, "Webcam 1"),
            R.id.cameraMonitorViewId
        );
    }
}
