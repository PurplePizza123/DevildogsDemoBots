package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class TeleOpMode extends CommandOpMode {
    @Override
    public void initialize() {
        Hardware hardware = new Hardware(this.hardwareMap);

        GamepadEx gamepad1 = new GamepadEx(this.gamepad1);
        GamepadEx gamepad2 = new GamepadEx(this.gamepad2);

        DriveSubsystem driveSubsystem = new DriveSubsystem(hardware, telemetry);
        IntakeSubsystem intakeSubsystem = new IntakeSubsystem(hardware, telemetry);

        DriveCommand driveCommand = new DriveCommand(
            driveSubsystem,
            ()->gamepad1.getLeftX(),
            ()->-gamepad1.getLeftY(),
            ()->gamepad1.getRightX()
        );

        IntakeInCommand intakeInCommand = new IntakeInCommand(intakeSubsystem);
        IntakeOutCommand intakeOutCommand = new IntakeOutCommand(intakeSubsystem);
        IntakeStopCommand intakeStopCommand = new IntakeStopCommand(intakeSubsystem);

        gamepad1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
            .whenActive(intakeInCommand)
            .whenInactive(intakeStopCommand);

        gamepad1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
            .whenActive(intakeOutCommand)
            .whenInactive(intakeOutCommand);


        driveSubsystem.setDefaultCommand(driveCommand);
        intakeSubsystem.setDefaultCommand(intakeInCommand);

        this.register(intakeSubsystem);
        this.register(driveSubsystem);
    }
}
