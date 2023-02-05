package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

import java.util.Arrays;

public class Commands {
    public static Subsystems subsystems;
    public static MenuCommands menu = new MenuCommands();
    public static DriveCommands drive = new DriveCommands();
    public static LiftCommands lift = new LiftCommands();
    public static IntakeCommands intake = new IntakeCommands();
    public static WaitCommands wait = new WaitCommands();
    public static AutoCommands auto = new AutoCommands();
    public static RandCommands rand = new RandCommands();
    public static JunctionCommands junction = new JunctionCommands();

    public Commands(Subsystems... subsystems) {
        Commands.subsystems = Arrays.stream(subsystems).findFirst().orElse(null);
    }
}
