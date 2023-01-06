package org.firstinspires.ftc.teamcode.game;

public enum Junction {
    GROUND(0.5), LOW(13.5), MEDIUM(23.5), HIGH(33.5);

    public final double height;

    Junction(double height) {
        this.height = height;
    }
}
