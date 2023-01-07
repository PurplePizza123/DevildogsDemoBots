package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.Commands;
import org.firstinspires.ftc.teamcode.modules.MenuModule;
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
        telemetry = new MultipleTelemetry(super.telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(super.hardwareMap);
        gamepad1 = new GamepadEx(super.gamepad1);
        gamepad2 = new GamepadEx(super.gamepad2);
        subsystems = new Subsystems(hardware, telemetry);
        commands = new Commands(subsystems);

        new MenuModule(this);

        // TODO solve for teleOp
        subsystems.drive.setPose(
            subsystems.nav.getStartPose(
                subsystems.menu.alliance,
                subsystems.menu.side
            )
        );

        while (!isStarted()) {
            CommandScheduler.getInstance().run();
            Thread.yield();
        }

        CommandScheduler.getInstance().clearButtons();

        schedule(
            commands.lift.calibrate()
        );
    }
}
