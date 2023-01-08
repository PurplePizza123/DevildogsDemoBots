package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;

public class MenuSubsystem extends HardwareSubsystem {
    public static boolean auto;
    public static Alliance alliance;
    public static Side side;
    public static int stacks;
    public static double delay;

    public MenuSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        stacks = 1; delay = 0;
    }

    @Override
    public void periodic() {
        if (auto) telemetry.addData("Menu", "%s alliance, %s side, %d stacks, %.1fs delay", alliance, side, stacks, delay);
    }
}
