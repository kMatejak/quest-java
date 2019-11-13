package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    CLOSED_DOORS(     "closedDoors"),
    OPENED_DOORS(     "openedDoors"),
    FIRE(             "fire"),
    STONES_LEFT_UP(   "stonesLeftUp"),
    STONES_UP(        "stonesUp"),
    STONES_RIGHT_UP(  "stonesRightUp"),
    STONES_LEFT(      "stonesLeft"),
    STONES_CENTER(    "stonesCenter"),
    STONES_RIGHT(     "stonesRight"),
    STONES_LEFT_DOWN( "stonesLeftDown"),
    STONES_DOWN(      "stonesDown"),
    STONES_RIGHT_DOWN("stonesRightDown"),
    ROCKET1(          "rocketPart1"),
    ROCKET2(          "rocketPart2"),
    ROCKET3(          "rocketPart3"),
    ROCKET4(          "rocketPart4"),
    RED_PLATFORM(     "redPlatform"),
    RED_GATE(         "redGate"),
    LETTER_Y(         "letterY"),
    LETTER_O(         "letterO"),
    LETTER_U(         "letterU"),
    LETTER_W(         "letterW"),
    LETTER_I(         "letterI"),
    LETTER_N(         "letterN");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
