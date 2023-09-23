package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

import java.util.Arrays;

public class Commands {
    public static Subsystems subsystems;
    public static MenuCommands menu = null; //new MenuCommands();
    public static DriveCommands drive = new DriveCommands();
    public static LiftCommands lift = null; //new LiftCommands();
    public static IntakeCommands intake = null; //new IntakeCommands();
    public static WaitCommands wait = null; //new WaitCommands();
    public static AutoCommands auto = null; //new AutoCommands();
    public static RandCommands rand = null; //new RandCommands();

    public Commands(Subsystems... subsystems) {
        Commands.subsystems = Arrays.stream(subsystems).findFirst().orElse(null);
    }
}
