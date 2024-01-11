package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.intake;
import static org.firstinspires.ftc.teamcode.commands.Commands.vision;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;

public class AutoCommands {
    public Command execute() {
        return wait.seconds(config.delay).alongWith(
            deposit.close(),
            drive.toRecognition(),
            vision.recognize()
        ).andThen(
            scorePurplePixel(),
            scoreYellowPixel(),
            park()
        );
    }

    public Command scorePurplePixel() {
        return drive.toSpikeMark().andThen(
            intake.out(),
            wait.seconds(0.25),
            intake.stop(),
            drive.toSpikeMarkTile()
        );
    }

    public Command scoreYellowPixel() {
        return drive.toBackdropApproach1().andThen(
            new SelectCommand(
                () -> vision.detect().andThen(
                    drive.toBackdropApproach2(),
                    drive.toBackdropSide(),
                    wait.seconds(1),
                    deposit.open(),
                    wait.seconds(2),
                    deposit.close()
                )
            )
        );
    }

    public Command scorePixel() {
        return drive.toBackdropApproach1().andThen(
            vision.detect(),
            drive.toBackdropFront(),
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
