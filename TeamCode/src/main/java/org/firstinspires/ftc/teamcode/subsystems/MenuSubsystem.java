package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

public class MenuSubsystem extends HardwareSubsystem {
    public static boolean enabled;
    public static Alliance alliance;
    public static Side side;
    public int stacks = 5;
    public double delay = 0;

    public MenuSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        if (enabled) telemetry.addData("Menu", "%s alliance, %s side, %d stacks, %.1fs delay", alliance, side, stacks, delay);
    }
}
