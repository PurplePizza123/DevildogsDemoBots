package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.hardware;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.game.Backdrop;

@Config
public class BackdropSubsystem extends SubsystemBase {
    @Override
    @SuppressLint("DefaultLocale")

    public void periodic() {
        if (config.auto) return;

        for (String line : Backdrop.lines("1-1L")) {
            telemetry.addLine("<font face=\"monospace\">" + line.replace(" ", "&nbsp;") + "</font>");
        }
    }
}
