package org.firstinspires.ftc.teamcode;

import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_435;
import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_84;
import static com.qualcomm.hardware.lynx.LynxModule.BulkCachingMode.MANUAL;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;

import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.LynxFirmware;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.adaptations.ftclib.MotorEx;
import org.firstinspires.ftc.teamcode.adaptations.ftclib.MotorGroup;

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

    public Encoder odometryRight;
    public Encoder odometryCenter;

    public Hardware(HardwareMap hardwareMap) {
        LynxFirmware.throwIfModulesAreOutdated(hardwareMap);

        modules = hardwareMap.getAll(LynxModule.class);

        for (LynxModule module : modules) {
            module.setBulkCachingMode(MANUAL);
        }

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();

        imu = hardwareMap.get(IMU.class, "imu");

        drive = new MotorGroup(
            driveFrontLeft = new MotorEx(hardwareMap, "driveLeftFront", RPM_435),
            driveFrontRight = new MotorEx(hardwareMap, "driveRightFront", RPM_435),
            driveBackLeft = new MotorEx(hardwareMap, "driveLeftRear", RPM_435),
            driveBackRight = new MotorEx(hardwareMap, "driveRightRear", RPM_435)
        );

//        odometryRight = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "odometryRightAndConveyor")));
//        odometryCenter = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "odometryCenter")));

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
