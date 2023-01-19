package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;

@Config
public class IntakeSubsystem extends HardwareSubsystem {
    public static double POWER_IN = 1.00;
    public static double POWER_OUT = 1.00;
    public static boolean PULSE_ENABLED = false;
    public static long PULSE_WIDTH = 500;
    public static long PULSE_PERIOD = 1500;
    private boolean pulse = false;

    public IntakeSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
    }

    @Override
    public void periodic() {
        telemetry.addData("Intake", "%.2f vel", hardware.intake.get());
        if (!PULSE_ENABLED || !pulse) return;
        long mod = System.currentTimeMillis() % PULSE_PERIOD;
        hardware.intake.set(mod < PULSE_WIDTH ? POWER_IN : 0);
    }

    public void in() {
        this.pulse = false;
        hardware.intake.set(+POWER_IN);
    }

    public void out() {
        this.pulse = false;
        hardware.intake.set(-POWER_OUT);
    }

    public void stop(boolean pulse) {
        this.pulse = pulse;
        hardware.intake.set(0);
    }
}
