package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class Subsystems {
    public ConfigSubsystem config;
    public MenuSubsystem menu;
    public JunctionSubsystem junction;
    public DriveSubsystem drive;
    public LiftSubsystem lift;
    public IntakeSubsystem intake;
    public RandSubsystem rand;
    public NavSubsystem nav;

    public Subsystems(Hardware hardware, Telemetry telemetry) {
        config = new ConfigSubsystem(hardware, telemetry);
        junction = new JunctionSubsystem(hardware, telemetry);
        menu = new MenuSubsystem(hardware, telemetry);
        drive = new DriveSubsystem(hardware, telemetry);
        lift = new LiftSubsystem(hardware, telemetry);
        intake = new IntakeSubsystem(hardware, telemetry);
        rand = new RandSubsystem(hardware, telemetry);
        nav = new NavSubsystem(hardware, telemetry);
    }
}
