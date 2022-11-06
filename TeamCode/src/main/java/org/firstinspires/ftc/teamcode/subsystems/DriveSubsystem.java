package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.TRACK_WIDTH;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.command.PurePursuitCommand;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.kinematics.Odometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.arcrobotics.ftclib.purepursuit.waypoints.EndWaypoint;
import com.arcrobotics.ftclib.purepursuit.waypoints.GeneralWaypoint;
import com.arcrobotics.ftclib.purepursuit.waypoints.StartWaypoint;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.hacks.MecanumOdometry;

@Config
public class DriveSubsystem extends HardwareSubsystem {
    public static double TRACK_WIDTH = 13.5;

    public MecanumDrive mecanum;
    public OdometrySubsystem odometry;

    public DriveSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);

        mecanum = new MecanumDrive(
            hardware.driveLeftFront,
            hardware.driveRightRear,
            hardware.driveLeftRear,
            hardware.driveRightRear
        );

        odometry = new OdometrySubsystem(
            new MecanumOdometry(
                new MecanumDriveKinematics(
                    new Translation2d(6.75,5.25),
                    new Translation2d(6.75,-5.25),
                    new Translation2d(-6.75,5.25),
                    new Translation2d(-6.75,-5.25)
                ),
                new Pose2d(0,0, new Rotation2d()),
                TRACK_WIDTH,
                () -> hardware.imu.getHeading(),
                () -> hardware.driveLeftFront.getVelocity(),
                () -> hardware.driveRightFront.getVelocity(),
                () -> hardware.driveLeftRear.getVelocity(),
                () -> hardware.driveRightRear.getVelocity()
            )
        );
    }

    public void drive(double strafe, double forward, double turn){
        mecanum.driveRobotCentric(strafe, forward, turn, true);
    }
}
