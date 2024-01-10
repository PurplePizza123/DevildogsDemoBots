package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.robotcore.external.Telemetry.DisplayFormat.HTML;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.adaptations.ftcdashboard.SampledTelemetry;
import org.firstinspires.ftc.teamcode.commands.Commands;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public abstract class OpMode extends CommandOpMode {
    public static SampledTelemetry telemetry;
    public static OpMode opMode;
    public static Hardware hardware;
    public static GamepadEx gamepad1;
    public static GamepadEx gamepad2;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().disable();
        CommandScheduler.getInstance().reset();

        super.telemetry.setDisplayFormat(HTML);

        telemetry = new SampledTelemetry(
            new MultipleTelemetry(
                super.telemetry,
                FtcDashboard.getInstance().getTelemetry()
            )
        );

        opMode = this;
        hardware = new Hardware(super.hardwareMap);
        gamepad1 = new GamepadEx(super.gamepad1);
        gamepad2 = new GamepadEx(super.gamepad2);

        Subsystems.initialize();
        Commands.initialize();
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
