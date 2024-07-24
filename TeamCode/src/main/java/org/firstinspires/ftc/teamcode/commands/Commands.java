package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.WaitCommand;

public class Commands {
    public static DriveCommands drive;
    public static AutoCommands auto;
    public static WaitCommands wait;

    public static void initialize() {
        drive = new DriveCommands();
        auto = new AutoCommands();
        wait = new WaitCommands();
    }
}
