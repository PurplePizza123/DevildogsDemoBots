package com.example.meepmeeptesting;

enum Alliance {
    BLUE(+1), RED(-1);

    public final int sign;

    Alliance(int sign) {
        this.sign = sign;
    }
}

enum Side {
    NORTH(+1), SOUTH(-1);

    public final int sign;

    Side(int sign) {
        this.sign = sign;
    }
}
