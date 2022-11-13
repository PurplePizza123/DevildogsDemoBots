package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class RedNorthOpMode extends OpMode {
    @Override
    public void initialize() {
        super.initialize();

        schedule(
            commands.autonomous.redNorth()
        );
    }
}
