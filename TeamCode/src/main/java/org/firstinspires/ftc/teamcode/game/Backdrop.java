package org.firstinspires.ftc.teamcode.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Backdrop {
    LEFT(+1), RIGHT(-1);

    public final int sign;

    Backdrop(int sign) {
        this.sign = sign;
    }

    private static String legend = "LCR-0123456789";

    private static String[][] characters = {
            { " ##      ", "  ###### ", " ######  ", "         ", "  ######  ", "  ## ", " ######  ", " ######  ", " ##   ## ", " ####### ", "  ######  ", " ####### ", "  #####  ", "  #####  "},
            { " ##      ", " ##      ", " ##   ## ", "         ", " ##    ## ", " ### ", "      ## ", "      ## ", " ##   ## ", " ##      ", " ##       ", "      ## ", " ##   ## ", " ##   ## "},
            { " ##      ", " ##      ", " ######  ", "  #####  ", " ##    ## ", "  ## ", "  #####  ", "  #####  ", " ####### ", " ####### ", " #######  ", "     ##  ", "  #####  ", "  ###### "},
            { " ##      ", " ##      ", " ##   ## ", "         ", " ##    ## ", "  ## ", " ##      ", "      ## ", "      ## ", "      ## ", " ##    ## ", "    ##   ", " ##   ## ", "      ## "},
            { " ####### ", "  ###### ", " ##   ## ", "         ", "  ######  ", "  ## ", " ####### ", " ######  ", "      ## ", " ####### ", "  ######  ", "   ##    ", "  #####  ", "  #####  "}
    };

    public static String[] lines (String address) {
        String[] lines = new String[6];
        Arrays.fill(lines, "");

        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < address.length(); j++) {
                char character = address.charAt(j);
                int index = legend.indexOf(character);
                lines[i] += characters[i][index];
            }
        }

        return lines;
    }
}
