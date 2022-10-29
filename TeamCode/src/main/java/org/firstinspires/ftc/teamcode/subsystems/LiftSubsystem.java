package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

public class LiftSubsystem extends HardwareSubsystem{
    public LiftSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    public void up(){hardware.lift.set(.1);
    }

    public void down(){hardware.lift.set(-.1);
    }

    public void stop(){
        hardware.lift.set(0);
    }
}
