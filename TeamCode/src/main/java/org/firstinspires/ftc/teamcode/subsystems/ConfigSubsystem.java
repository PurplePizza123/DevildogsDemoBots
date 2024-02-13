package org.firstinspires.ftc.teamcode.subsystems;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;
import static org.firstinspires.ftc.teamcode.game.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.game.Alliance.RED;
import static org.firstinspires.ftc.teamcode.game.Backdrop.LEFT;
import static org.firstinspires.ftc.teamcode.game.Backdrop.RIGHT;
import static org.firstinspires.ftc.teamcode.game.Config.config;
import static org.firstinspires.ftc.teamcode.game.Parking.INNER;
import static org.firstinspires.ftc.teamcode.game.Parking.OUTER;
import static org.firstinspires.ftc.teamcode.game.Side.NORTH;
import static org.firstinspires.ftc.teamcode.game.Side.SOUTH;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.opMode;
import static org.firstinspires.ftc.teamcode.opmodes.OpMode.telemetry;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystems.drive;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.game.Config;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConfigSubsystem extends SubsystemBase {
    private static final String fileName = "config.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static boolean PERSISTENCE = false;
    public static double DELAY_INCREMENT = 0.5;
    public static double OFFSET_INCREMENT = 0.5;
    private static Thread thread;

    private int index = 0;
    private boolean editable = false;

    @SuppressLint("DefaultLocale")
    private final List<Item> items = Arrays.asList(
        new Item(
            "Alliance",
            () -> String.format("%s", config.alliance),
            change -> { config.alliance = config.alliance == RED ? BLUE : RED; drive.resetPose(); }
        ),
        new Item(
            "Side",
            () -> String.format("%s", config.side),
            change -> { config.side = config.side == NORTH ? SOUTH : NORTH; drive.resetPose(); }
        ),
        new Item(
            "Delay",
            () -> String.format("%.1fs", config.delay),
            change -> config.delay = clamp(config.delay + DELAY_INCREMENT * change.sign, 0, 30)
        ),
        new Item(
            "Parking",
            () -> String.format("%s", config.parking),
            change -> config.parking = config.parking == INNER ? OUTER : INNER
        ),
        new Item(
            "Backdrop",
            () -> String.format("%s", config.backdrop),
            change -> config.backdrop = config.backdrop == LEFT ? RIGHT : LEFT
        ),
        new Item(
            "Stacks",
            () -> String.format("%s", config.stackTimes),
            change -> config.stackTimes = config.stackTimes == 0 ? 1 : 0
        )
    );

    public ConfigSubsystem() {
        if (config == null && PERSISTENCE) {
            File file = AppUtil.getInstance().getSettingsFile(fileName);

            if (file.exists()) {
                try {
                    config = gson.fromJson(ReadWriteFile.readFile(file), Config.class);
                    Log.i(this.getClass().getSimpleName(), "Config loaded");
                } catch (Exception e) {
                    Log.w(this.getClass().getSimpleName(), "Config failed to load", e);
                }
            }
        }

        if (config == null) config = new Config();

        config.auto = opMode.getClass().isAnnotationPresent(Autonomous.class);
        config.started = false;
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void periodic() {
        if (config.auto) {
            for (Item item : items) telemetry.addData(getCaption(item.key), item.telemetrySupplier);
            telemetry.addLine("-----------------------------------------------------------------------------");
        }

        if (!PERSISTENCE || (thread != null && thread.isAlive())) return;

        thread = new Thread() {
            @Override public void run() {
                String json = gson.toJson(config);
                File file = AppUtil.getInstance().getSettingsFile(fileName);
                ReadWriteFile.writeFile(file, json);
            }
        };

        thread.start();
    }

    public void start() {
        if (config.started) return;
        config.timer.reset();
        config.started = true;
        Log.i(this.getClass().getSimpleName(), "Start | " + gson.toJson(config));
    }

    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }

    public void changeItem(Change change)
    {
        if (editable) index = clamp(index + change.sign, 0, items.size() - 1);
    }

    public void changeValue(Change change)
    {
        if (editable) items.get(index).changeConsumer.accept(change);
    }

    private String getCaption(String key) {
        return String.format("%sConfig (%s)", editable && items.get(index).key.equals(key) ? ">" : "", key);
    }

    public static class Item {
        public String key;
        public Supplier<String> telemetrySupplier;
        public Consumer<Change> changeConsumer;

        public Item(String key, Supplier<String> telemetrySupplier, Consumer<Change> changeConsumer) {
            this.key = key;
            this.telemetrySupplier = telemetrySupplier;
            this.changeConsumer = changeConsumer;
        }
    }

    public enum Change {
        PREV(-1), NEXT(1);

        public final int sign;

        Change(int sign) {
            this.sign = sign;
        }
    }
}
