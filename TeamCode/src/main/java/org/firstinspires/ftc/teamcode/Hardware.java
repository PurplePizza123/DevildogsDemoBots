package org.firstinspires.ftc.teamcode;

import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_312;
import static com.arcrobotics.ftclib.hardware.motors.Motor.GoBILDA.RPM_435;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hacks.MotorEx;
import org.firstinspires.ftc.teamcode.roadrunner.util.Encoder;
import org.firstinspires.ftc.teamcode.roadrunner.util.LynxModuleUtil;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

public class Hardware {

    public VoltageSensor batteryVoltageSensor;
    public BNO055IMU imu;

    public MotorGroup drive;
    public MotorEx driveLeftFront;
    public MotorEx driveRightFront;
    public MotorEx driveLeftRear;
    public MotorEx driveRightRear;

    public Encoder odometryLeft;
    public Encoder odometryRight;
    public Encoder odometryCenter;

    public MotorGroup intake;

    public MotorEx lift;
    public DigitalChannel liftLeftLimit;
    public DigitalChannel liftRightLimit;

    public OpenCvWebcam signalWebcam;

    public Hardware(HardwareMap hardwareMap) {
        LynxModuleUtil.ensureMinimumFirmwareVersion(hardwareMap);

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();

        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        drive = new MotorGroup(
            driveLeftFront = new MotorEx(hardwareMap, "driveLeftFront", RPM_312),
            driveRightFront = new MotorEx(hardwareMap, "driveRightFront", RPM_312),
            driveLeftRear = new MotorEx(hardwareMap, "driveLeftRear", RPM_312),
            driveRightRear = new MotorEx(hardwareMap, "driveRightRear", RPM_312)
        );

        odometryLeft = new Encoder(hardwareMap.get(DcMotorEx.class, "odometryLeft"));
        odometryRight = new Encoder(hardwareMap.get(DcMotorEx.class, "odometryRight"));
        odometryCenter = new Encoder(hardwareMap.get(DcMotorEx.class, "odometryCenter"));

        intake = new MotorGroup(
            new CRServo(hardwareMap, "intakeFront"),
            new CRServo(hardwareMap, "intakeLeft"),
            new CRServo(hardwareMap, "intakeRight")
        );

        lift = new MotorEx(hardwareMap, "lift", RPM_435);
        liftLeftLimit = hardwareMap.get(DigitalChannel.class, "liftLeftLimit");
        liftRightLimit = hardwareMap.get(DigitalChannel.class, "liftRightLimit");

        signalWebcam = OpenCvCameraFactory.getInstance().createWebcam(
            hardwareMap.get(WebcamName.class, "Webcam 1"),
            R.id.cameraMonitorViewId
        );
    }
}
