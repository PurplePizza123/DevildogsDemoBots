package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.hardware.motors.Motor.Direction.REVERSE;
import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.VelocityControl;
import static com.arcrobotics.ftclib.hardware.motors.Motor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

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
    public static double MAX_SPEED = 0.5;
    public static double TRACK_WIDTH = 13.5;
    public static double TRACK_DEPTH = 10.5;
    public static Motor.RunMode RUN_MODE = VelocityControl;
    public static Motor.ZeroPowerBehavior ZERO_POWER_BEHAVIOR = BRAKE;
    public static boolean DRIVE_FIELD_CENTRIC = false;
    public static boolean SQUARE_INPUTS = true;
    public static boolean AUTO_INVERT = false;

    public MecanumDrive mecanum;
    public OdometrySubsystem odometry;

    public DriveSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);

        hardware.imu.init();

        hardware.driveLeftFront.setInverted(true);
        hardware.driveRightFront.setInverted(false);
        hardware.driveRightFront.encoder.setDirection(REVERSE); // HACK
        hardware.driveLeftRear.setInverted(true);
        hardware.driveRightRear.setInverted(false);

        hardware.drive.setRunMode(RUN_MODE);
        hardware.drive.setZeroPowerBehavior(ZERO_POWER_BEHAVIOR);
        hardware.drive.setDistancePerPulse(DISTANCE_PER_PULSE);
        hardware.drive.resetEncoder();

        mecanum = new MecanumDrive(
            AUTO_INVERT,
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
                () -> +hardware.driveLeftFront.getRate(),
                () -> -hardware.driveRightFront.getRate(), // HACK
                () -> +hardware.driveLeftRear.getRate(),
                () -> +hardware.driveRightRear.getRate()
            )
        );
    }

    @Override
    public void periodic() {
        telemetry.addData("Drive (Heading)","%.2f deg", hardware.imu.getHeading());
        telemetry.addData("Drive (Pose)", "x:%.2f, y:%.2f, h:%.2f", odometry.getPose().getX(), odometry.getPose().getY(), odometry.getPose().getHeading());
        telemetry.addData("Drive (LF)","%.2f vel, %.2f rt, %.2f dist", hardware.driveLeftFront.getVelocity(), hardware.driveLeftFront.getRate(), hardware.driveLeftFront.getDistance());
        telemetry.addData("Drive (RF)","%.2f vel, %.2f rt, %.2f dist", -hardware.driveRightFront.getVelocity(), -hardware.driveRightFront.getRate(), hardware.driveRightFront.getDistance());
        telemetry.addData("Drive (LR)","%.2f vel, %.2f rt, %.2f dist", hardware.driveLeftRear.getVelocity(), hardware.driveLeftRear.getRate(), hardware.driveLeftRear.getDistance());
        telemetry.addData("Drive (RR)","%.2f vel, %.2f rt, %.2f dist", hardware.driveRightRear.getVelocity(), hardware.driveRightRear.getRate(), hardware.driveRightRear.getDistance());
    }

    public void inputs(double strafe, double forward, double turn) {
        if (DRIVE_FIELD_CENTRIC) mecanum.driveFieldCentric(strafe, forward, turn, hardware.imu.getHeading(), SQUARE_INPUTS);
        else mecanum.driveRobotCentric(strafe, forward, turn, SQUARE_INPUTS);
    }

    public void move(double drive, double strafe, double heading, double inches) {
        double remainder = getRemainderLeftToTurn(heading);
        if (drive != 0)
            drive = clamp(0.2, drive, (inches - hardware.drive.getDistance()) / (12));
        if (strafe != 0)
            strafe = clamp(0.2, strafe, (inches - hardware.drive.getDistance()) / (12));
        double turn = remainder / 45;
        inputs(drive, strafe, turn);
    }

    public void turn(double power, double heading) {
        power = Math.abs(power);
        double remainder = getRemainderLeftToTurn(heading);
        double turn = clamp(0.2, power, remainder / 45 * power);
        inputs(0, 0,turn);
    }

    public void stop(){
        inputs(0,0,0);
    }

    public double getDistance(){
        return hardware.drive.getDistance();
    }

    private double clamp(double min, double max, double value) {
        return value >= 0 ?
                Math.min(max, Math.max(min, value)) :
                Math.min(-min, Math.max(-max, value));
    }

    public double getRemainderLeftToTurn(double heading) {
        double remainder = hardware.imu.getHeading() - heading;
        if (remainder > +180) remainder -= 360;
        if (remainder < -180) remainder += 360;
        return remainder;
    }

    public void resetEncoders() {
        hardware.drive.resetEncoder();
    }

}
