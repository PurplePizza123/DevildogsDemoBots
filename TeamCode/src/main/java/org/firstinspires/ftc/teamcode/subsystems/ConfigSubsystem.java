package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.game.Config;

import java.io.File;

public class ConfigSubsystem extends HardwareSubsystem {
    private static final String fileName = "config.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigSubsystem(Hardware hardware, Telemetry telemetry) {
        super(hardware, telemetry);
        File file = AppUtil.getInstance().getSettingsFile(fileName);
        if (!file.exists()) return;
        String json = ReadWriteFile.readFile(file);
        config = gson.fromJson(json, Config.class);
        if (config == null) config = new Config();
    }

    @Override
    public void periodic() {
        new Thread() {
            @Override public void run() {
                String json = gson.toJson(config);
                File file = AppUtil.getInstance().getSettingsFile(fileName);
                ReadWriteFile.writeFile(file, json);
            }
        }.start();
    }
}
