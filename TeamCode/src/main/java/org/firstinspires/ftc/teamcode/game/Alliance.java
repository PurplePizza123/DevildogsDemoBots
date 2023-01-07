package org.firstinspires.ftc.teamcode.game;

public enum Alliance {
    BLUE(+1), RED(-1);

    public final int sign;

    Alliance(int sign) {
        this.sign = sign;
    }
}
