package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem.delay;

import com.arcrobotics.ftclib.command.Command;

public class VisionCommands extends Commands {
    public Command detect() {
        return wait.until(
            () -> !subsystems.vision.getDetectionLabel().equals("none")
        ).withTimeout(3000).alongWith(
            wait.seconds(delay)
        );
    }
}
