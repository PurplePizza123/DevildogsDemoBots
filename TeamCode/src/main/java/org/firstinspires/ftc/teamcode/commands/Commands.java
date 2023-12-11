package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class Commands {
    public static WaitCommands wait;
    public static AutoCommands auto;
    public static VisionCommands vision;
    public static MenuCommands menu;
    public static DriveCommands drive;
    public static SweeperCommands sweeper;
    public static DroneCommands drone;
    public static DepositCommands deposit;
    public static HoistCommands hoist;
    public static ConveyorCommands conveyor;
    public static LiftCommands lift;
    public static IntakeCommands intake;

    public static void initialize() {
        wait = new WaitCommands();
        auto = new AutoCommands();
        vision = new VisionCommands();
        menu = new MenuCommands();
        drive = new DriveCommands();
        sweeper = new SweeperCommands();
        conveyor = new ConveyorCommands();
        intake = new IntakeCommands();
        lift = new LiftCommands();
        deposit = new DepositCommands();
        hoist = new HoistCommands();
        drone = new DroneCommands();
    }
}
