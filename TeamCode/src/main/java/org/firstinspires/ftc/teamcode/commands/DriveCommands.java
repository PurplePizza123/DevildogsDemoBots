package org.firstinspires.ftc.teamcode.commands;


import static org.firstinspires.ftc.teamcode.commands.Commands.drive;
import static org.firstinspires.ftc.teamcode.commands.Commands.wait;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.nav;


import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SelectCommand;


import org.firstinspires.ftc.teamcode.adaptations.roadrunner.Trajectory;
import org.firstinspires.ftc.teamcode.subsystems.Subsystems;


import java.util.function.Consumer;
import java.util.function.DoubleSupplier;


@SuppressWarnings({"unused"})
public class DriveCommands {
    public Command setDrivePower(double power) {
        return new InstantCommand(() -> Subsystems.drive.power = power, Subsystems.drive);
    }


    public Command input(DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn) {
        return new RunCommand(
                () -> Subsystems.drive.inputs(
                        forward.getAsDouble(),
                        strafe.getAsDouble(),
                        turn.getAsDouble()
                ), Subsystems.drive
        );
    }


    public Command forward(double distance) {
        return drive.follow(
                t -> t.strafeToLinearHeading(
                        nav.getForwardPose(distance)
                )
        );
    }


    public Command strafe(double distance) {
        return drive.follow(
                t -> t.strafeToLinearHeading(
                        nav.getStrafePose(distance)
                )
        );
    }


    public Command turn(double heading) {
        return drive.follow(
                t -> t.turnTo(
                        Subsystems.drive.getPose().heading.toDouble() + Math.toRadians(heading)
                )
        );
    }


    public Command toPose(double x, double y, double heading) {
        return drive.follow(
                t -> t.strafeToLinearHeading(
                        new Pose2d(x, y, Math.toRadians(heading))
                )
        );
    }


    public Command follow(Consumer<Trajectory> trajectory) {
        return complete(
                () -> Subsystems.drive.followTrajectoryAsync(trajectory)
        );
    }


    private Command complete(Runnable runnable) {
        return new SelectCommand(
                () -> new InstantCommand(runnable, Subsystems.drive)
        ).andThen(
                wait.until(() -> !Subsystems.drive.isBusy())
        );
    }
}


