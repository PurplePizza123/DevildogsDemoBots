package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.VelocityControl;
import static com.arcrobotics.ftclib.hardware.motors.Motor.ZeroPowerBehavior.BRAKE;

import com.acmerobotics.dashboard.config.Config;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.MecanumOdometry;

@Config
public class DriveSubsystem extends HardwareSubsystem {
    public static double PULSE_PER_ROTATION = 537.5;
    public static double DISTANCE_PER_ROTATION = 3.95 * Math.PI;
    public static double DISTANCE_PER_PULSE = DISTANCE_PER_ROTATION / PULSE_PER_ROTATION;
    public static double MAX_SPEED = 1;
    public static double TRACK_WIDTH = 13.5;
    public static double TRACK_DEPTH = 10.5;
    public static boolean LEFT_FRONT_INVERT = true;
    public static boolean RIGHT_FRONT_INVERT = true;
    public static boolean LEFT_REAR_INVERT = true;
    public static boolean RIGHT_REAR_INVERT = true;
    public static Motor.RunMode RUN_MODE = VelocityControl;
    public static Motor.ZeroPowerBehavior ZERO_POWER_BEHAVIOR = BRAKE;

    public MecanumDrive mecanum;
    public OdometrySubsystem odometry;

    public DriveSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);

        hardware.imu.init();

        hardware.driveLeftFront.setInverted(LEFT_FRONT_INVERT);
        hardware.driveRightFront.setInverted(RIGHT_FRONT_INVERT);
        hardware.driveLeftRear.setInverted(LEFT_REAR_INVERT);
        hardware.driveRightRear.setInverted(RIGHT_REAR_INVERT);

        hardware.drive.setRunMode(RUN_MODE);
        hardware.drive.setZeroPowerBehavior(ZERO_POWER_BEHAVIOR);
        hardware.drive.setDistancePerPulse(DISTANCE_PER_PULSE);
        hardware.drive.resetEncoder();

        mecanum = new MecanumDrive(
            hardware.driveLeftFront,
            hardware.driveRightFront,
            hardware.driveLeftRear,
            hardware.driveRightRear
        );

        mecanum.setMaxSpeed(MAX_SPEED);

        odometry = new OdometrySubsystem(
            new MecanumOdometry(
                new MecanumDriveKinematics(
                    new Translation2d(+TRACK_DEPTH / 2, +TRACK_WIDTH / 2),
                    new Translation2d(+TRACK_DEPTH / 2, -TRACK_WIDTH / 2),
                    new Translation2d(-TRACK_DEPTH / 2, +TRACK_WIDTH / 2),
                    new Translation2d(-TRACK_DEPTH / 2, -TRACK_WIDTH / 2)
                ),
                new Pose2d(0,0, new Rotation2d()),
                TRACK_WIDTH,
                () -> hardware.imu.getRotation2d(),
                () -> hardware.driveLeftFront.getVelocity(),
                () -> hardware.driveRightFront.getVelocity(),
                () -> hardware.driveLeftRear.getVelocity(),
                () -> hardware.driveRightRear.getVelocity()
            )
        );
    }

    @Override
    public void periodic() {
        telemetry.addData("Drive (Heading)","%.2f deg", hardware.imu.getHeading());
        telemetry.addData("Drive (LF)","%.2f vel, %.2f dist", hardware.driveLeftFront.getVelocity(),hardware.driveLeftFront.getDistance());
        telemetry.addData("Drive (RF)","%.2f vel, %.2f dist", hardware.driveRightFront.getVelocity(),hardware.driveRightFront.getDistance());
        telemetry.addData("Drive (LR)","%.2f vel, %.2f dist", hardware.driveLeftRear.getVelocity(),hardware.driveLeftRear.getDistance());
        telemetry.addData("Drive (RR)","%.2f vel, %.2f dist", hardware.driveRightRear.getVelocity(),hardware.driveRightRear.getDistance());
    }

    public void move(double strafe, double forward, double turn) {
        mecanum.driveRobotCentric(strafe, forward, turn, true);
    }
}
