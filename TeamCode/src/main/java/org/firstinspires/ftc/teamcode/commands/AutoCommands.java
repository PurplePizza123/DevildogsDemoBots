package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.Commands.auto;
import static org.firstinspires.ftc.teamcode.commands.Commands.deposit;
import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.intake;
import static org.firstinspires.ftc.teamcode.commands.Commands.lift;
import static org.firstinspires.ftc.teamcode.commands.Commands.vision;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import java.util.Timer;

public class AutoCommands {
    public Command execute() {
        return wait.seconds(config.delay).andThen(
            lift.to(0),
            deposit.close(),
            drive.toRecognition(),
            vision.recognize()
        ).andThen(
            vision.setActiveCamera(hardware.rearWebcam),
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
            lift.to(0),
            drive.toBackdropApproach() //TODO Maybe switch to park?
        );
    }

    public Command scoreStackPixels() {
        return new SelectCommand(
            () -> {
                SequentialCommandGroup group = new SequentialCommandGroup();

                if (config.stackTimes > 0 && config.timer.seconds() <= 20) {
                    group.addCommands(
                        drive.toStackApproach1().andThen(
                            drive.toStackApproach2(),
                            intake.in(),
                            drive.toStack(),
                            wait.seconds(1),
                            intake.stop(),
                            drive.toStackApproach1(),
                            new ConditionalCommand(auto.scorePixel(), wait.seconds(0), () -> config.timer.seconds() <= 25),
                            auto.park()
                        )
                    );
                }

                return group;
            }
        );
    }

    public Command park() {
        return drive.toParking();
    }
}
