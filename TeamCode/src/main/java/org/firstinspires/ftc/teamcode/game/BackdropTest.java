package org.firstinspires.ftc.teamcode.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum BackdropTest {
    GROUND(4.0), LOW(13.5), MEDIUM(23.5), HIGH(33.5);

    public final double height;

    BackdropTest(double height) {
        this.height = height;
    }

    private static final Map<String, BackdropTest> map = new HashMap<>();

//    static {
//        Arrays.asList("V1", "V3", "V5", "X1", "X3", "X5", "Z1", "Z3", "Z5").forEach(junction -> map.put(junction, GROUND));
//        Arrays.asList("V2", "V4", "W1", "W5", "Y1", "Y5", "Z2", "Z4").forEach(junction -> map.put(junction, LOW));
//        Arrays.asList("W2", "W4", "Y2", "Y4").forEach(junction -> map.put(junction, MEDIUM));
//        Arrays.asList("W3", "X2", "X4", "Y3").forEach(junction -> map.put(junction, HIGH));
//    }

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
