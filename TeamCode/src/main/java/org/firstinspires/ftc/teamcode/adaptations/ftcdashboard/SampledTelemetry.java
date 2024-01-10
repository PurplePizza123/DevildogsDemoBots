package org.firstinspires.ftc.teamcode.adaptations.ftcdashboard;

import android.util.Log;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class SampledTelemetry {
    public static double SAMPLES_PER_SECOND = 4;

    private final ElapsedTime timer = new ElapsedTime();

    private final Telemetry telemetry;

    private boolean sample = false;

    public SampledTelemetry(Telemetry telemetry) {
        this(telemetry, SAMPLES_PER_SECOND);
    }

    public SampledTelemetry(Telemetry telemetry, double samplesPerSecond) {
        this.telemetry = telemetry;
        SAMPLES_PER_SECOND = samplesPerSecond;
    }

    public void addData(String caption, Func<String> func) {
        if (sample) {
            try {
                telemetry.addData(caption, func.value());
            } catch (Exception e) {
                // TODO: Determine why april tag metadata is crashing the bot sometimes
                Log.e("SampledTelemetry", "Error adding telemetry while evaluating `func.value()`", e);
            }
        }
    }

    public void update() {
        if (sample) {
            telemetry.update();
            timer.reset();
        }

        sample = timer.seconds() > 1 / SAMPLES_PER_SECOND;
    }
}
