package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.commands.AutonomousCommands.Plan.A;
import static org.firstinspires.ftc.teamcode.commands.AutonomousCommands.Side.LEFT;
import static org.firstinspires.ftc.teamcode.commands.AutonomousCommands.Side.RIGHT;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.AutonomousCommands;

public class MenuSubsystem extends HardwareSubsystem{
    public static boolean enabled = true;
    public static AutonomousCommands.Side side = RIGHT;
    public AutonomousCommands.Plan plan = A;
    public double delay = 0;
    public int stacks = 2;

    public MenuSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        if (enabled) telemetry.addData("Menu", "%s side, %s plan, %d stacks, %.1f delay", side, plan, stacks, delay);
    }
}
