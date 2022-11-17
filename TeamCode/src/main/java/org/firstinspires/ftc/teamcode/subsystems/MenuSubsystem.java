package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.commands.AutonomousCommands.Plan.A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.AutonomousCommands;

public class MenuSubsystem extends HardwareSubsystem{
    public AutonomousCommands.Side side;
    public AutonomousCommands.Plan plan = A;
    public double delay = 0;

    public MenuSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData("Menu", "%s side, plan %s, %.1f delay", side, plan, delay);
    }
}
