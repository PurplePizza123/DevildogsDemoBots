package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.Commands;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public abstract class OpMode extends CommandOpMode {
    public Telemetry telemetry = new MultipleTelemetry(super.telemetry, FtcDashboard.getInstance().getTelemetry());
    public Hardware hardware = new Hardware(super.hardwareMap);
    public GamepadEx gamepad1 = new GamepadEx(super.gamepad1);
    public GamepadEx gamepad2 = new GamepadEx(super.gamepad2);
    public Subsystems subsystems = new Subsystems(hardware, telemetry);
    public Commands commands = new Commands(subsystems);
}
