package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class Commands {
    public static Subsystems subsystems;
    public static MenuCommands menu;
    public static DriveCommands drive;
    public static SweeperCommands sweeper;
    public static DroneCommands drone;
    public static DepositCommands deposit;
    public static HoistCommands hoist;
    public static ConveyorCommands conveyor;
    public static LiftCommands lift;
    public static IntakeCommands intake;
    public static WaitCommands wait;
    public static AutoCommands auto;
    public static RandCommands rand;

    public static void initialize() {
        menu = new MenuCommands();
        drive = new DriveCommands();
        wait = new WaitCommands();
        auto = new AutoCommands();
        drone = new DroneCommands();
        intake = new IntakeCommands();
        conveyor = new ConveyorCommands();
        sweeper = new SweeperCommands();
    }
}
