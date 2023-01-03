package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Game.Plan.A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Game;
import org.firstinspires.ftc.teamcode.Hardware;

public class MenuSubsystem extends HardwareSubsystem {
    public static boolean enabled;
    public static Game.Side side;
    public Game.Plan plan = A;
    public int stacks = 2;
    public double delay = 0;

    public MenuSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        if (enabled) telemetry.addData("Menu", "%s side, %s plan, %d stacks, %.1fs delay", side, plan, stacks, delay);
    }
}
