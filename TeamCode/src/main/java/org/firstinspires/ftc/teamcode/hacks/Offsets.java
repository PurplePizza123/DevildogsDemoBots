package org.firstinspires.ftc.teamcode.hacks;

import java.util.function.Consumer;

public class Offsets {
    public boolean y1st;
    public double startX;
    public double startY;
    public double startTileX;
    public double startTileY;
    public double midTileX;
    public double midTileY;
    public double endTileX;
    public double endTileY;
    public double endX;
    public double endY;

    public Offsets set(Consumer<Offsets>... consumers) {
        for (Consumer<Offsets> consumer : consumers)
            consumer.accept(this);
        return this;
    }
}
