package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class Commands {
    public static Subsystems subsystems;
    public static DriveCommands drive = new DriveCommands();
    public static LiftCommands lift = new LiftCommands();
    public static IntakeCommands intake = new IntakeCommands();

    public Commands() { }

    public Commands(Subsystems subsystems) {
        Commands.subsystems = subsystems;
    }
}
