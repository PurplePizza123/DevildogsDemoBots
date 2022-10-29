package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class IntakeSubsystem extends HardwareSubsystem{
    public IntakeSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    public void in(){
        hardware.intake.setPosition(1);
    }

    public void out(){
        hardware.intake.setPosition(0);
     }

    public void stop(){
        hardware.intake.setPosition(0.5);
    }
}
