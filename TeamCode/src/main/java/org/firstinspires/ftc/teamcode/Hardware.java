package org.firstinspires.ftc.teamcode;

import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_435;
import static com.qualcomm.hardware.lynx.LynxModule.BulkCachingMode.MANUAL;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.hacks.MotorEx;
import org.firstinspires.ftc.teamcode.roadrunner.util.Encoder;
import org.firstinspires.ftc.teamcode.roadrunner.util.LynxModuleUtil;

import java.util.List;

public class Hardware {
    private List<LynxModule> modules;

    public VoltageSensor batteryVoltageSensor;

    public IMU imu;
    public YawPitchRollAngles imuAngles;
    public AngularVelocity imuVelocities;

    public MotorGroup drive;
    public MotorEx driveLeftFront;
    public MotorEx driveRightFront;
    public MotorEx driveLeftRear;
    public MotorEx driveRightRear;

    public Encoder odometryLeft;
    public Encoder odometryRight;
    public Encoder odometryCenter;

    public MotorGroup conveyor;
    public CRServo sweeperLeft;
    public CRServo sweeperRight;
    public CRServo sweeperCenter;

    public MotorEx lift;
    public DigitalChannel liftLeftLimit;
    public DigitalChannel liftRightLimit;

    public WebcamName frontWebcam;
    public WebcamName rearWebcam;

    public RevBlinkinLedDriver lights;

    public Hardware(HardwareMap hardwareMap) {
        LynxModuleUtil.ensureMinimumFirmwareVersion(hardwareMap);

        modules = hardwareMap.getAll(LynxModule.class);

        for (LynxModule module : modules) {
            module.setBulkCachingMode(MANUAL);
        }

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();

        imu = hardwareMap.get(IMU.class, "imu");

        drive = new MotorGroup(
            driveLeftFront = new MotorEx(hardwareMap, "driveLeftFront", RPM_435),
            driveRightFront = new MotorEx(hardwareMap, "driveRightFront", RPM_435),
            driveLeftRear = new MotorEx(hardwareMap, "driveLeftRear", RPM_435),
            driveRightRear = new MotorEx(hardwareMap, "driveRightRear", RPM_435)
        );

//        odometryLeft = new Encoder(hardwareMap.get(DcMotorEx.class, "odometryLeft"));
//        odometryRight = new Encoder(hardwareMap.get(DcMotorEx.class, "odometryRight"));
//        odometryCenter = new Encoder(hardwareMap.get(DcMotorEx.class, "odometryCenter"));
//
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
//        frontWebcam = hardwareMap.get(WebcamName.class, "frontWebcam");
//        rearWebcam = hardwareMap.get(WebcamName.class, "rearWebcam");
//
//        lights = hardwareMap.get(RevBlinkinLedDriver.class,"lights");
    }

    public void clearBulkCache() {
        for (LynxModule module : modules) {
            module.clearBulkCache();
        }

        imuAngles = imu.getRobotYawPitchRollAngles();
        imuVelocities = imu.getRobotAngularVelocity(RADIANS);
    }
}
