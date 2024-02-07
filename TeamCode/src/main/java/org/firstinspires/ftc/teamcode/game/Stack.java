package org.firstinspires.ftc.teamcode.game;

public enum Stack {
    OUTER(+1), INNER(-1), CENTER(0); //TODO Brandon how do I do this

    public final int number;

    Stack(int number) {
        this.number = number;
    }
}
