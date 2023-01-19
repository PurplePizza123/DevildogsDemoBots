package org.firstinspires.ftc.teamcode.game;

public enum Side {
    NORTH(+1), SOUTH(-1);

    public final int sign;

    Side(int sign) {
        this.sign = sign;
    }
}