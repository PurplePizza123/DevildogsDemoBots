package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.ConveyorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HoistSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

import java.util.Arrays;

public class Commands {
    public static Subsystems subsystems;
    public static MenuCommands menu = null; //new MenuCommands();
    public static DriveCommands drive = new DriveCommands();
    public static SweeperCommands sweeper = new SweeperCommands();
    public static DroneCommands drone = new DroneCommands();
    public static HoistCommands hoist = new HoistCommands();
    public static ConveyorCommands conveyor = new ConveyorCommands();
    public static LiftCommands lift = null; //new LiftCommands();
    public static IntakeCommands intake = new IntakeCommands();
    public static WaitCommands wait = new WaitCommands();
    public static AutoCommands auto = null; //new AutoCommands();
    public static RandCommands rand = null; //new RandCommands();

    public Commands(Subsystems... subsystems) {
        Commands.subsystems = Arrays.stream(subsystems).findFirst().orElse(null);
    }
}
