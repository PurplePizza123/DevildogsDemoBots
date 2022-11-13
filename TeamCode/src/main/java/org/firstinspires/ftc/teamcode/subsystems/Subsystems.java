package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class Subsystems {
    public DriveSubsystem drive;
    public LiftSubsystem lift;
    public IntakeSubsystem intake;
    public VisionSubsystem vision;

    public Subsystems(Hardware hardware, Telemetry telemetry) {
        drive = new DriveSubsystem(hardware, telemetry);
        lift = new LiftSubsystem(hardware, telemetry);
        intake = new IntakeSubsystem(hardware, telemetry);
        vision = new VisionSubsystem(hardware, telemetry);
    }
}
