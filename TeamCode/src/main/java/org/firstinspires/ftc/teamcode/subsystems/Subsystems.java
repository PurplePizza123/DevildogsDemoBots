package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class Subsystems {
    public ConfigSubsystem config;
    public MenuSubsystem menu;
    public DriveSubsystem drive;
    public LiftSubsystem lift;
    public ConveyorSubsystem conveyor;
    public SweeperSubsystem sweeper;
    public RandSubsystem rand;
    public ObjectsSubsystem objects;
    public NavSubsystem nav;
    public LightSubsystem light;
    public PeriodicSubsystem periodic;

    public Subsystems(Hardware hardware, Telemetry telemetry) {
        config = new ConfigSubsystem(hardware, telemetry);
        menu = new MenuSubsystem(hardware, telemetry);
        drive = new DriveSubsystem(hardware, telemetry);
//        lift = new LiftSubsystem(hardware, telemetry);
//        intake = new IntakeSubsystem(hardware, telemetry);
//        rand = new RandSubsystem(hardware, telemetry);
//        nav = new NavSubsystem(hardware, telemetry);
//        light = new LightSubsystem(hardware, telemetry);
//        objects = new ObjectsSubsystem(hardware, telemetry);
        periodic = new PeriodicSubsystem(hardware, telemetry);
    }
}
