package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class IntakeSubsystem extends HardwareSubsystem{
    public IntakeSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    public void in(){
        hardware.intakeFront.setPosition(1);
        hardware.intakeLeft.setPosition(1);
        hardware.intakeRight.setPosition(1);
    }

    public void out(){
        hardware.intakeFront.setPosition(0);
        hardware.intakeLeft.setPosition(0);
        hardware.intakeRight.setPosition(0);
     }

    public void stop(){
        hardware.intakeFront.setPosition(0.5);
        hardware.intakeLeft.setPosition(.5);
        hardware.intakeRight.setPosition(.5);
    }
}
