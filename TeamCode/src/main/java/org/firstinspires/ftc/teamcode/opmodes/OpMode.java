package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.robotcore.external.Telemetry.DisplayFormat.HTML;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.Commands;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public abstract class OpMode extends CommandOpMode {
    public Telemetry telemetry;
    public Hardware hardware;
    public GamepadEx gamepad1;
    public GamepadEx gamepad2;
    public Subsystems subsystems;
    public Commands commands;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().disable();
        CommandScheduler.getInstance().reset();

        super.telemetry.setDisplayFormat(HTML);

        telemetry = new MultipleTelemetry(super.telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(super.hardwareMap);
        gamepad1 = new GamepadEx(super.gamepad1);
        gamepad2 = new GamepadEx(super.gamepad2);
        subsystems = new Subsystems(hardware, telemetry);
        commands = new Commands(subsystems);

        config.auto = this.getClass().isAnnotationPresent(Autonomous.class);
    }

    public void waitForStart() {
        while (!isStarted()) {
            CommandScheduler.getInstance().run();
            Thread.yield();
        }

        config.timer.reset();
        config.started = true;
    }
}
