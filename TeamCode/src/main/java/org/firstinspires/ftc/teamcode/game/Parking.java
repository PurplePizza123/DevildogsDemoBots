package org.firstinspires.ftc.teamcode.game;

public enum Parking {
    OUTER(+1), INNER(-1);

    public final int sign;

    Parking(int sign) {
        this.sign = sign;
    }
}
