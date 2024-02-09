package org.firstinspires.ftc.teamcode.controls;

import org.firstinspires.ftc.teamcode.commands.BucketCommands;

/** @noinspection InstantiationOfUtilityClass*/
public class Controls {
    public static void initializeAuto() {
        new ConfigControl();
    }

    public static void initializeTeleop() {
        new DriveControl();
        new IntakeControl();
        new LiftControl();
        new DepositControl();
        new HoistControl();
        new DroneControl();
        new ConfigControl();
        new BackdropControl();
    }
}
