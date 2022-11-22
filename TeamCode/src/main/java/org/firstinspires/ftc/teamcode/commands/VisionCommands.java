package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.RunCommand;

public class VisionCommands extends Commands {
    public Command detect() {
        return new RunCommand(subsystems.vision::detect, subsystems.vision).raceWith(
            wait.until(() -> subsystems.vision.getDetectionId() != 0).withTimeout(3000)
        ).alongWith(wait.seconds(subsystems.menu.delay));
    }
}