package tictactoe.logic;

import java.util.HashMap;
import java.util.Map;

public enum Player {
    HUMAN(-1),
    BLANK(0),
    COMPUTER(1);

    private int value;
    private static Map map = new HashMap<>();

    private Player(int value) {
        this.value = value;
    }

    static {
        for (Player pageType : Player.values()) {
            map.put(pageType.value, pageType);
        }
    }

}
