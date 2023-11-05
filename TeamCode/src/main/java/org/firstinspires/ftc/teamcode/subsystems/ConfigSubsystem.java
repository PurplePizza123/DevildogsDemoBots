package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.game.Config;

import java.io.File;

public class ConfigSubsystem extends SubsystemBase {
    private static final String fileName = "config.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static Thread thread;

    public ConfigSubsystem() {
        if (config != null) return;
        File file = AppUtil.getInstance().getSettingsFile(fileName);
        if (file.exists()) config = gson.fromJson(ReadWriteFile.readFile(file), Config.class);
        if (config == null) config = new Config();
    }

    @Override
    public void periodic() {
        if (thread != null && thread.isAlive()) return;

        thread = new Thread() {
            @Override public void run() {
                String json = gson.toJson(config);
                File file = AppUtil.getInstance().getSettingsFile(fileName);
                ReadWriteFile.writeFile(file, json);
            }
        };

        thread.start();
    }
}
