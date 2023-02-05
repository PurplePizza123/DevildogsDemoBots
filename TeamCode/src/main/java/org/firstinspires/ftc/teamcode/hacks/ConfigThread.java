package org.firstinspires.ftc.teamcode.hacks;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.game.Config;

import java.io.File;

public class ConfigThread extends Thread {
    private static final String fileName = "config.json";
    private Gson gson = new GsonBuilder().create();

    public ConfigThread() {
        File file = AppUtil.getInstance().getSettingsFile(fileName);
        if (!file.exists()) return;
        String json = ReadWriteFile.readFile(file);
        config = gson.fromJson(json, new TypeToken<Config>() {}.getType());
        if (config == null) config = new Config();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String json = gson.toJson(config);
                File file = AppUtil.getInstance().getSettingsFile(fileName);
                ReadWriteFile.writeFile(file, json);
                sleep(50);
            }
        } catch (Exception e) {
            // NO-OP
        }
    }
}
