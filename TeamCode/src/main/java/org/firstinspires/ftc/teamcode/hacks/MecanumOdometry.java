package org.firstinspires.ftc.teamcode.hacks;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.kinematics.Odometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveWheelSpeeds;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class MecanumOdometry extends Odometry {
    private MecanumDriveOdometry odometry;
    private Supplier<Rotation2d> gyroAngle;
    private DoubleSupplier leftFrontSpeed;
    private DoubleSupplier rightFrontSpeed;
    private DoubleSupplier leftRearSpeed;
    private DoubleSupplier rightRearSpeed;

    public MecanumOdometry(
        MecanumDriveKinematics kinematics,
        Pose2d robotPose,
        double trackWidth,
        Supplier<Rotation2d> gyroAngle,
        DoubleSupplier leftFrontSpeed,
        DoubleSupplier rightFrontSpeed,
        DoubleSupplier leftRearSpeed,
        DoubleSupplier rightRearSpeed
    ) {
        super(robotPose, trackWidth);
        odometry = new MecanumDriveOdometry(kinematics, gyroAngle.get(), robotPose);
        this.gyroAngle = gyroAngle;
        this.leftFrontSpeed = leftFrontSpeed;
        this.rightFrontSpeed = rightFrontSpeed;
        this.leftRearSpeed = leftRearSpeed;
        this.rightRearSpeed = rightRearSpeed;
    }

    @Override
    public void updatePose(Pose2d newPose) {
        robotPose = newPose;
    }

    @Override
    public void updatePose() {
        robotPose = odometry.updateWithTime(
            System.currentTimeMillis() / 1000d,
            gyroAngle.get(),
            new MecanumDriveWheelSpeeds(
                leftFrontSpeed.getAsDouble(),
                rightFrontSpeed.getAsDouble(),
                leftRearSpeed.getAsDouble(),
                rightRearSpeed.getAsDouble()
            )
        );
    }
}
