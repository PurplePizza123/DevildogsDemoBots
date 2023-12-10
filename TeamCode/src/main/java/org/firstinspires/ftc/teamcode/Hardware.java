package org.firstinspires.ftc.teamcode;

import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_117;
import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_1620;
import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_435;
import static com.qualcomm.hardware.lynx.LynxModule.BulkCachingMode.MANUAL;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;

import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.LynxFirmware;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.adaptations.ftclib.MotorEx;

import java.util.List;

public class Hardware {
    public List<LynxModule> modules;

    public VoltageSensor batteryVoltageSensor;

    public IMU imu;
    public YawPitchRollAngles imuAngles;
    public AngularVelocity imuVelocities;

    public MotorGroup drive;
    public MotorEx driveFrontLeft;
    public MotorEx driveFrontRight;
    public MotorEx driveBackLeft;
    public MotorEx driveBackRight;

//    public MotorEx hoist;
    public MotorEx conveyor;


    public Encoder odometryRight;
    public Encoder odometryCenter;

    public CRServo sweeperLeft;
    public CRServo sweeperRight;
    public CRServo sweeperCenter;

//    public CRServo hoistHelp;
    public Servo drone;

    public Servo deposit;

    public MotorEx lift;
    public DigitalChannel liftLimit;
    public DigitalChannel liftRightLimit;

    public WebcamName frontWebcam;
    public WebcamName rearWebcam;

    public RevBlinkinLedDriver lights;

    public Hardware(HardwareMap hardwareMap) {
        LynxFirmware.throwIfModulesAreOutdated(hardwareMap);

        modules = hardwareMap.getAll(LynxModule.class);

        for (LynxModule module : modules) {
            module.setBulkCachingMode(MANUAL);
        }

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();

        imu = hardwareMap.get(IMU.class, "imu");

        drive = new MotorGroup(
            driveFrontLeft = new MotorEx(hardwareMap, "driveFrontLeft", RPM_435),
            driveFrontRight = new MotorEx(hardwareMap, "driveFrontRight", RPM_435),
            driveBackLeft = new MotorEx(hardwareMap, "driveBackLeft", RPM_435),
            driveBackRight = new MotorEx(hardwareMap, "driveBackRight", RPM_435)
        );

        odometryRight = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "odometryRight")));
        odometryCenter = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "odometryCenter")));

        drone = hardwareMap.get(Servo.class, "drone");

//        hoistHelp = hardwareMap.get(CRServo.class,"hoistHelp");

        conveyor = new MotorEx(hardwareMap, "conveyor", RPM_435);

        deposit = hardwareMap.get(Servo.class, "deposit");

//        hoist = new MotorEx(hardwareMap, "hoist", RPM_1620);

        lift = new MotorEx(hardwareMap, "lift", RPM_117);

//        intake = new MotorGroup(
//            intakeFrontLeft = new CRServo(hardwareMap, "intakeFrontLeft"),
//            intakeFrontRight = new CRServo(hardwareMap, "intakeFrontRight"),
//            intakeRearLeft = new CRServo(hardwareMap, "intakeRearLeft"),
//            intakeRearRight = new CRServo(hardwareMap, "intakeRearRight")
//        );
//
//        lift = new MotorEx(hardwareMap, "lift", RPM_435);
//        liftLeftLimit = hardwareMap.get(DigitalChannel.class, "liftLeftLimit");
//        liftRightLimit = hardwareMap.get(DigitalChannel.class, "liftRightLimit");
//
        frontWebcam = hardwareMap.get(WebcamName.class, "frontWebcam");
//        rearWebcam = hardwareMap.get(WebcamName.class, "rearWebcam");
//
//        lights = hardwareMap.get(RevBlinkinLedDriver.class,"lights");

        clearBulkCache();
    }

    public void clearBulkCache() {
        for (LynxModule module : modules) {
            module.clearBulkCache();
        }

        imuAngles = imu.getRobotYawPitchRollAngles();
        imuVelocities = imu.getRobotAngularVelocity(RADIANS);
    }
}
