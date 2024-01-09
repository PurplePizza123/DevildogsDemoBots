package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.vision;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class VisionCommands {
    public Command recognize() {
        return vision.enableRecognition().andThen(
            wait.seconds(.5),
            attemptRecognition(),
            disableRecognition(),
            logRecognition()
        );
    }

    private Command attemptRecognition() {
        return wait.until(
            () -> Subsystems.vision.recognition != null
        ).withTimeout(1500);
    }

    private Command enableRecognition() {
        return new InstantCommand(
            () -> VisionSubsystem.recognitionEnabled = true,
            Subsystems.vision
        );
    }

    private Command disableRecognition() {
        return new InstantCommand(
            () -> VisionSubsystem.recognitionEnabled = false,
            Subsystems.vision
        );
    }

    public Command detect() {
        return vision.enableDetection().andThen(
            attemptDetection(),
            logDetection()
        );
    }

    private Command attemptDetection() {
        return wait.until(
            () -> Subsystems.vision.detection != null
        ).withTimeout(3000);
    }

    private Command enableDetection() {
        return new InstantCommand(
            () -> VisionSubsystem.detectionEnabled = true,
            Subsystems.vision
        );
    }

    private Command disableDetection() {
        return new InstantCommand(
            () -> VisionSubsystem.detectionEnabled = false,
            Subsystems.vision
        );
    }

    private Command logRecognition() {
        return new InstantCommand(
            Subsystems.vision::logRecognition,
            Subsystems.vision
        );
    }

    private Command logDetection() {
        return new InstantCommand(
            Subsystems.vision::logDetection,
            Subsystems.vision
        );
    }
}
