package org.firstinspires.ftc.teamcode.game;

public enum Alliance {
    UNKNOWN(0), BLUE(+1), RED(-1);

    public final int sign;

    Alliance(int sign) {
        this.sign = sign;
    }
}
