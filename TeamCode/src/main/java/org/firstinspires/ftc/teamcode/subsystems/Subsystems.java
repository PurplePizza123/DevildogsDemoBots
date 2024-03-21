package org.firstinspires.ftc.teamcode.subsystems;

public class Subsystems {
    public static DriveSubsystem drive;
    public static PeriodicSubsystem periodic;

    public static void initialize() {
        drive = new DriveSubsystem();
        periodic = new PeriodicSubsystem();
    }
}
