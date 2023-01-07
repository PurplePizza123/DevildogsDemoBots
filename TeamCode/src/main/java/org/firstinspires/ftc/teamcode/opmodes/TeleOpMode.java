package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Side;
import org.firstinspires.ftc.teamcode.modules.DriveModule;
import org.firstinspires.ftc.teamcode.modules.IntakeModule;
import org.firstinspires.ftc.teamcode.modules.LiftModule;
import org.firstinspires.ftc.teamcode.subsystems.MenuSubsystem;

@TeleOp(name = "Teleop")
@SuppressWarnings("unused")
public class TeleOpMode extends OpMode {
    @Override
    public void initialize() {
        MenuSubsystem.enabled = false;
        if (MenuSubsystem.alliance == null) MenuSubsystem.alliance = Alliance.BLUE;
        if (MenuSubsystem.side == null) MenuSubsystem.side = Side.SOUTH;

        super.initialize();

        new DriveModule(this);
        new LiftModule(this);
        new IntakeModule(this);
    }
}
