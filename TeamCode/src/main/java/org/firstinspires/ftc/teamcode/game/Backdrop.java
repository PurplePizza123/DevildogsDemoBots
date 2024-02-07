package org.firstinspires.ftc.teamcode.game;

import static org.firstinspires.ftc.teamcode.game.Config.config;

import android.inputmethodservice.Keyboard;

import java.util.Arrays;

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

    private static String[] oddRowCols = {
            "1L", "1R", "2L", "2R", "3L", "3R"
    };

    private static String[] evenRowCols = {
            "1L", "1C", "12", "2C", "23", "3C", "3R"
    };

    public static String getAddress() {
        return config.backdropRow + "-" + (
            config.backdropRow % 2 == 0 ?
                evenRowCols[config.backdropCol - 1] :
                oddRowCols[config.backdropCol - 1]
        );
    }

    public static String[] lines() {
        String address = getAddress();
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
