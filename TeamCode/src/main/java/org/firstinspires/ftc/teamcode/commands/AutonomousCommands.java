package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.Game.Plan.A;
import static org.firstinspires.ftc.teamcode.Game.Plan.B;
import static org.firstinspires.ftc.teamcode.Game.Side.LEFT;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.GROUND;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.HIGH;
import static org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem.LiftPosition.STACK;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import java.util.HashMap;

public class AutonomousCommands extends Commands {
    public Command execute() {
        return vision.detect().andThen(
            autonomous.scoreStartCone(),
            autonomous.executeChosenPlan(),
            autonomous.park()
        );
    }

    public Command executeChosenPlan() {
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(A, autonomous.scoreStack(subsystems.menu.stacks));
                put(B, autonomous.prepareToPark());
            }}, () -> subsystems.menu.plan
        );
    }

    public Command prepareToPark() {
        return drive.turn(1, adapt(90));
    }

    public Command scoreStartCone() {
        return intake.getCone().andThen(
            drive.move(0, 1, 4),
            drive.move(adapt(1), 0, 26),
            drive.move(0, 1, 24).alongWith(lift.to(HIGH)),
            drive.turn(1, adapt(-45)),
            drive.move(0, 1, 11.5),
            intake.setCone()
        );
    }

    public Command scoreStack(int times) {
        int stackedCones = 5;

        SequentialCommandGroup group = new SequentialCommandGroup(
            drive.turn(1, 0),
            drive.move(0, 1, 24).alongWith(lift.to(STACK)),
            drive.turn(1, adapt(90))
        );

        while (--times >= 0) {
            group.addCommands(
                drive.move(0, 1, 49.5),
                intake.getCone(--stackedCones).andThen(wait.seconds(0.3)),
                drive.move(0, -1, 49.5).alongWith(lift.to(HIGH)),
                drive.turn(1, adapt(-135)),
                drive.move(0, 1, 11.5),
                intake.setCone(stackedCones),
                drive.turn(1, adapt(90))
            );
        }

        return group;
    }

    public Command park() {
        boolean isLeft = subsystems.menu.side == LEFT;
        int detectionId = subsystems.vision.getDetectionId() + 1;
        String detectionLabel = subsystems.vision.getDetectionLabel();
        return new SelectCommand(
            new HashMap<Object, Command>() {{
                put(1, drive.move(0, 1, isLeft ? 46 : 0));
                put(2, drive.move(0, 1, 24));
                put(3, drive.move(0, 1, isLeft ? 0 : 46));
            }}, () -> detectionLabel == "none" ? (isLeft ? 3 : 1) : detectionId
        ).alongWith(lift.to(GROUND).andThen(wait.seconds(3)));
    }

    public double adapt(double value) {
        return subsystems.menu.side.sign * value;
    }
}
