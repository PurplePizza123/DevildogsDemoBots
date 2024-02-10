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
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class AutoCommands {
    public Command execute() {
        return wait.seconds(config.delay).alongWith(
            deposit.close(),
            lift.to(0),
            drive.toRecognition(),
            vision.recognize(),
            vision.setActiveCamera(hardware.rearWebcam)
        ).andThen(
            scorePurplePixel(),
            scoreYellowPixel(),
            scoreStackPixels(),
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
        return scorePixel();
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
            drive.toBackdropApproach() //TODO Maybe switch to park?
        );
    }

    public Command scoreStackPixels() {
        SequentialCommandGroup group = new SequentialCommandGroup();

        if (config.stackTimes > 0) {
            group.addCommands(
                drive.toStackApproach1().andThen(
                    drive.toStackApproach2(),
                    intake.in(),
                    drive.toStack(),
                    wait.seconds(1),
                    intake.stop(),
                    drive.toStackApproach1(),
                    drive.toBackdrop(),
                    deposit.open(),
                    wait.seconds(.5),
                    deposit.close()
                )
            );
        }

        return group;
    }

    public Command park() {
        return drive.toParking();
    }
}
