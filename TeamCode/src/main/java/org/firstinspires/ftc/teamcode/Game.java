package org.firstinspires.ftc.teamcode;

public class Game {
    public enum Side {
        LEFT(1), RIGHT(-1), NORTH(1), SOUTH(-1);

        public final int sign;

        Side(int sign) {
            this.sign = sign;
        }
    }

    public enum Plan {
        A, B
    }
}
