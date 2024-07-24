package org.firstinspires.ftc.teamcode.subsystems;

public class Subsystems {
    public static DriveSubsystem drive;
    public static NavSubsystem nav;
    public static PeriodicSubsystem periodic;

    public static void initialize() {
        drive = new DriveSubsystem();
        nav = new NavSubsystem();
        periodic = new PeriodicSubsystem();
    }
}
