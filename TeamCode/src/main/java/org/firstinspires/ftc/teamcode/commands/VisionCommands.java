package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.wait;

import com.arcrobotics.ftclib.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.Subsystems;

public class VisionCommands {
    public Command detect() {
        return wait.until(
            () -> Subsystems.vision.detection != null
        ).withTimeout(3000);
    }
}
