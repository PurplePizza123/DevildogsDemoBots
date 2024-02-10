package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.intake;
import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.commands.Commands.vision;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;

public class AutoCommands {
    public Command execute() {
        return wait.seconds(config.delay).alongWith(
            deposit.close(),
            drive.toRecognition(),
            vision.recognize(),
            vision.setActiveCamera(hardware.rearWebcam)
        ).andThen(
            scorePurplePixel(),
            scoreYellowPixel(),
            park()
        );
    }

    public Command scorePurplePixel() {
        return drive.toSpikeMark().andThen(
            intake.out(-0.3).andThen(
                wait.seconds(0.6),
                intake.stop()
            ).alongWith(
                wait.seconds(0.3),
                drive.toSpikeMarkTile()
            )
        );
    }

    public Command scoreYellowPixel() {
        return drive.toBackdropApproach().andThen(
            new SelectCommand(
                () -> vision.detect().andThen(
                    drive.toBackdropSide(),
                    wait.seconds(0.5),
                    deposit.open(),
                    wait.seconds(2),
                    deposit.close()
                )
            )
        );
    }

    public Command scorePixel() {
        return drive.toBackdropApproach().andThen(
            vision.detect(),
            lift.toScorePos(),
            drive.toBackdrop(),
            wait.seconds(0.5),
            deposit.open(),
            wait.seconds(2),
            deposit.close(),
            drive.toBackdropApproach()
        );
    }

    public Command scoreStackPixels() {
        return drive.toPixelStack().andThen(
            intake.in(),
            drive.toBackdrop(),
            deposit.open(),
            wait.seconds(.5),
            deposit.close()
        );
    }

    public Command park() {
        return drive.toParking();
    }
}
