package org.firstinspires.ftc.teamcode.subsystems;

public class Subsystems {
    public static ConfigSubsystem config;
    public static MenuSubsystem menu;
    public static DriveSubsystem drive;
    public static NavSubsystem nav;
    public static VisionSubsystem vision;
    public static LiftSubsystem lift;
    public static ConveyorSubsystem conveyor;
    public static SweeperSubsystem sweeper;
    public static DepositSubsystem deposit;
    public static HoistSubsystem hoist;
    public static DroneSubsystem drone;
    public static LightSubsystem light;
    public static PeriodicSubsystem periodic;

    public static void initialize() {
        menu = new MenuSubsystem();
        drive = new DriveSubsystem();
        nav = new NavSubsystem();
        vision = new VisionSubsystem();
        conveyor = new ConveyorSubsystem();
        sweeper = new SweeperSubsystem();
        drone = new DroneSubsystem();
        periodic = new PeriodicSubsystem();
        deposit = new DepositSubsystem();
        lift = new LiftSubsystem();
    }
}
