package org.firstinspires.ftc.teamcode.subsystems;

public class Subsystems {
    public static ConfigSubsystem config;
    public static MenuSubsystem menu;
    public static VisionSubsystem vision;
    public static ConveyorSubsystem conveyor;
    public static SweeperSubsystem sweeper;
    public static LiftSubsystem lift;
    public static DepositSubsystem deposit;
    public static DroneSubsystem drone;
    public static HoistSubsystem hoist;
    public static DriveSubsystem drive;
    public static NavSubsystem nav;
    public static LightSubsystem light;
    public static PeriodicSubsystem periodic;

    public static void initialize() {
        menu = new MenuSubsystem();
        vision = new VisionSubsystem();
        conveyor = new ConveyorSubsystem();
        sweeper = new SweeperSubsystem();
        lift = new LiftSubsystem();
        deposit = new DepositSubsystem();
        drone = new DroneSubsystem();
        hoist = new HoistSubsystem();
        drive = new DriveSubsystem();
        nav = new NavSubsystem();
        light = new LightSubsystem();
        periodic = new PeriodicSubsystem();
    }
}
