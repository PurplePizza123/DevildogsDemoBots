package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

public class AutoOpMode extends OpMode {
    @Override
    public void initialize() {
        MenuSubsystem.enabled = true;
        DriveSubsystem.MAX_POWER = 1;

        super.initialize();

        schedule(
      //      commands.autonomous.execute()
        );
    }
}
