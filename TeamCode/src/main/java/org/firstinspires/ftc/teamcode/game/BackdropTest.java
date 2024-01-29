package org.firstinspires.ftc.teamcode.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum BackdropTest {
    L1(7.25), L2(L1 + PIXEL_DIAGONAL_HEIGHT), L3(L2 + PIXEL_DIAGONAL_HEIGHT), L4(L3 + PIXEL_DIAGONAL_HEIGHT),
         L5(L4 + PIXEL_DIAGONAL_HEIGHT), L6(L5 + PIXEL_DIAGONAL_HEIGHT), L7(L6 + PIXEL_DIAGONAL_HEIGHT), L8(L7 + PIXEL_DIAGONAL_HEIGHT),
         L9(L8 + PIXEL_DIAGONAL_HEIGHT), L10(L9 + PIXEL_DIAGONAL_HEIGHT), L11(L10 + PIXEL_DIAGONAL_HEIGHT); //TODO These are all to mid not top or above

    public final double PIXEL_DIAGONAL_HEIGHT = 3.3499106299;

    public final double height;

    BackdropTest(double height) {
        this.height = height;
    }

    private static final Map<String, BackdropTest> map = new HashMap<>();

    static {
        Arrays.asList("L1").forEach(backdrop -> map.put(backdrop, L1));
        Arrays.asList("V2", "V4", "W1", "W5", "Y1", "Y5", "Z2", "Z4").forEach(junction -> map.put(junction, LOW));
        Arrays.asList("W2", "W4", "Y2", "Y4").forEach(junction -> map.put(junction, MEDIUM));
        Arrays.asList("W3", "X2", "X4", "Y3").forEach(junction -> map.put(junction, HIGH));
    }

    public static BackdropTest get(String label) {
        return map.get(label);
    }

    private static String legend = "L1L2L3L4L5L6L7L8L9L10L11123456"; //TODO Ask Brandon about how to separate

    private static String[][] characters = {
        { " #         #   ", " #        #####   ", "  #        #####  ", " #       #       ", " #       ####### ", " #        #####  ", " #       ####### ", " #        #####  ", " #        #####  ", " #         #     ###   ", " #         #     #   ", "   #   ", "  #####  ", "  #####  ", " #       ", " ####### ", "  #####  ", " ####### ", "    #####  ", "  #####  ", "   #     ###   ", "   #     #   ", "   #    #####  ", "   #    #####  " },
        { " #        ##   ", " #       #     #  ", "  #       #     # ", " #       #    #  ", " #       #       ", " #       #     # ", " #       #    #  ", " #       #     # ", " #       #     # ", " #        ##    #   #  ", " #        ##    ##   ", "  ##   ", " #     # ", " #     # ", " #    #  ", " #       ", " #     # ", "      #  ", "   #     # ", " #     # ", "  ##    #   #  ", "  ##    ##   ", "  ##   #     # ", "  ##   #     # " },
        { " #       # #   ", " #             #  ", "  #             # ", " #       #    #  ", " #       #       ", " #       #       ", " #           #   ", " #       #     # ", " #       #     # ", " #       # #   #     # ", " #       # #   # #   ", " # #   ", "       # ", "       # ", " #    #  ", " #       ", " #       ", "     #   ", "   #     # ", " #     # ", " # #   #     # ", " # #   # #   ", " # #         # ", " # #         # " },
        { " #         #   ", " #        #####   ", "  #        #####  ", " #       #    #  ", " #       ######  ", " #       ######  ", " #          #    ", " #        #####  ", " #        ###### ", " #         #   #     # ", " #         #     #   ", "   #   ", "  #####  ", "  #####  ", " ####### ", "  ###### ", "  ###### ", "    #    ", "    #####  ", "  ###### ", "   #   #     # ", "   #     #   ", "   #    #####  ", "   #    #####  " },
        { " #         #   ", " #       #        ", "  #             # ", " #       ####### ", " #             # ", " #       #     # ", " #         #     ", " #       #     # ", " #             # ", " #         #   #     # ", " #         #     #   ", "   #   ", " #       ", "       # ", "      #  ", "       # ", " #     # ", "   #     ", "   #     # ", "       # ", "   #   #     # ", "   #     #   ", "   #   #       ", "   #         # " },
        { " #         #   ", " #       #        ", "  #       #     # ", " #            #  ", " #       #     # ", " #       #     # ", " #         #     ", " #       #     # ", " #       #     # ", " #         #    #   #  ", " #         #     #   ", "   #   ", " #       ", " #     # ", "      #  ", " #     # ", " #     # ", "   #     ", "   #     # ", " #     # ", "   #    #   #  ", "   #     #   ", "   #   #       ", "   #   #     # " },
        { " ####### ##### ", " ####### #######  ", "  #######  #####  ", " #######      #  ", " #######  #####  ", " #######  #####  ", " #######   #     ", " #######  #####  ", " #######  #####  ", " ####### #####   ###   ", " ####### ##### ##### ", " ##### ", " ####### ", "  #####  ", "      #  ", "  #####  ", "  #####  ", "   #     ", "    #####  ", "  #####  ", " #####   ###   ", " ##### ##### ", " ##### ####### ", " #####  #####  " }
            };

    public static String[] lines (String junction) {
        String[] lines = new String[6];
        Arrays.fill(lines, "");

        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < junction.length(); j++) {
                char character = junction.charAt(j);
                int index = legend.indexOf(character);
                lines[i] += characters[i][index];
            }
        }

        return lines;
    }
}
