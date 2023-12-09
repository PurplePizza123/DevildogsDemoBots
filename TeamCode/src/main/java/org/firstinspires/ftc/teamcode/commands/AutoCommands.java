package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.drone;
import static org.firstinspires.ftc.teamcode.commands.Commands.vision;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;

public class AutoCommands {
    public Command execute() {
        return wait.seconds(config.delay).alongWith(
            deposit.close(),
            vision.recognize()
        ).andThen(
            scorePurplePixel(),
            scoreYellowPixel(),
            park()
        );
    }

    public Command scorePurplePixel() {
        return drive.toSpikeMark().andThen(
            drive.toSpikeMarkTile()
        );
    }

    public Command scoreYellowPixel() {
        return drive.toBackdropApproach1().andThen(
            vision.detect(),
            drive.toBackdropApproach2(),
            drive.toBackdrop(),
            wait.seconds(1),
            deposit.open(),
            wait.seconds(2),
            deposit.close()
        );
    }

    public Command park() {
        return drive.toParking();
    }
}
