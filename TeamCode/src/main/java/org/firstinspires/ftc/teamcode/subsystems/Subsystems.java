package org.firstinspires.ftc.teamcode.subsystems;

import org.checkerframework.checker.units.qual.C;

public class Subsystems {
    public static ConfigSubsystem config;
    public static MenuSubsystem menu;
    public static DriveSubsystem drive;
    public static LiftSubsystem lift;
    public static ConveyorSubsystem conveyor;
    public static SweeperSubsystem sweeper;
    public static DroneSubsystem drone;
    public static HoistSubsystem hoist;
    public static DepositSubsystem deposit;
    public static RandSubsystem rand;
    public static ObjectsSubsystem objects;
    public static NavSubsystem nav;
    public static LightSubsystem light;
    public static PeriodicSubsystem periodic;

    public static void initialize() {
        menu = new MenuSubsystem();
        drive = new DriveSubsystem();
        nav = new NavSubsystem();
        rand = new RandSubsystem();
        periodic = new PeriodicSubsystem();
        drone = new DroneSubsystem();
        conveyor = new ConveyorSubsystem();
        sweeper = new SweeperSubsystem();
    }
}
