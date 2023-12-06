package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.robotcore.external.Telemetry.DisplayFormat.HTML;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.Commands;
import org.firstinspires.ftc.teamcode.game.Config;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public abstract class OpMode extends CommandOpMode {
    public static Telemetry telemetry;
    public static Hardware hardware;
    public static GamepadEx gamepad1;
    public static GamepadEx gamepad2;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().disable();
        CommandScheduler.getInstance().reset();

        super.telemetry.setDisplayFormat(HTML);

//        telemetry = new MultipleTelemetry(super.telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry = super.telemetry;
        hardware = new Hardware(super.hardwareMap);
        gamepad1 = new GamepadEx(super.gamepad1);
        gamepad2 = new GamepadEx(super.gamepad2);

        config = new Config();

        config.auto = this.getClass().isAnnotationPresent(Autonomous.class);

        Subsystems.initialize();
        Commands.initialize();

        VisionSubsystem.recognitionEnabled = config.auto;
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
