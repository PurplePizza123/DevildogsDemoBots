package org.firstinspires.ftc.teamcode.game;

public enum Backdrop {
    LEFT(+1), RIGHT(-1);

    public final int sign;

    Backdrop(int sign) {
        this.sign = sign;
    }
}
