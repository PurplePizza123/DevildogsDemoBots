package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

public class VisionCommands extends Commands{
    public Command detect() {
        return new RunCommand(subsystems.vision::detect, subsystems.vision).raceWith(
            new WaitUntilCommand(() -> subsystems.vision.getDetectionId() != 0).withTimeout(3000)
        ).alongWith(wait.seconds(subsystems.menu.delay));
    }
}