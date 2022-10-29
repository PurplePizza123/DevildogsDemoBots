package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class DriveSubsystem extends HardwareSubsystem {

    private MecanumDrive mecanumDrive;

    public DriveSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);

        mecanumDrive = new MecanumDrive(
            hardware.driveLeftFront,
            hardware.driveRightRear,
            hardware.driveLeftRear,
            hardware.driveRightRear
        );
    }

    public void drive(double strafe, double forward, double turn){
        mecanumDrive.driveRobotCentric(strafe, forward, turn, true);
    }
}
