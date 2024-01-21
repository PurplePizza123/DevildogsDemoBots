package org.firstinspires.ftc.teamcode.commands;

public class Commands {
    public static WaitCommands wait;
    public static AutoCommands auto;
    public static VisionCommands vision;
    public static ConfigCommands menu;
    public static DriveCommands drive;
    public static DroneCommands drone;
    public static DepositCommands deposit;
    public static HoistCommands hoist;
    public static ConveyorCommands conveyor;
    public static LiftCommands lift;
    public static IntakeCommands intake;
    public static BucketCommands bucket;

    public static void initialize() {
        wait = new WaitCommands();
        auto = new AutoCommands();
        vision = new VisionCommands();
        menu = new ConfigCommands();
        drive = new DriveCommands();
        conveyor = new ConveyorCommands();
        intake = new IntakeCommands();
        lift = new LiftCommands();
        deposit = new DepositCommands();
        hoist = new HoistCommands();
        drone = new DroneCommands();
        bucket = new BucketCommands();
    }
}
