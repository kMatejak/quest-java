package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty",           new Tile(0, 0));
        tileMap.put("floor",           new Tile(2, 0));
        tileMap.put("wall",            new Tile(0, 13));

        tileMap.put("stonesLeftUp",    new Tile(18, 0));
        tileMap.put("stonesUp",        new Tile(19, 0));
        tileMap.put("stonesRightUp",   new Tile(20, 0));
        tileMap.put("stonesLeft",      new Tile(18, 1));
        tileMap.put("stonesCenter",    new Tile(19, 1));
        tileMap.put("stonesRight",     new Tile(20, 1));
        tileMap.put("stonesLeftDown",  new Tile(18, 2));
        tileMap.put("stonesDown",      new Tile(19, 2));
        tileMap.put("stonesRightDown", new Tile(20, 2));

        tileMap.put("closedDoors",     new Tile(3,  3));
        tileMap.put("openedDoors",     new Tile(4,  3));
        tileMap.put("redGate",         new Tile(13, 18));
        tileMap.put("redPlatform",     new Tile(12, 18));

        tileMap.put("rocketPart1",     new Tile(15, 20));
        tileMap.put("rocketPart2",     new Tile(15, 21));
        tileMap.put("rocketPart3",     new Tile(15, 22));
        tileMap.put("rocketPart4",     new Tile(15, 23));

        tileMap.put("fire",            new Tile(15, 10));
        tileMap.put("key",             new Tile(16, 23));
        tileMap.put("sword",           new Tile(0,  30));
        tileMap.put("redKey",          new Tile(18, 23));

        tileMap.put("ghost",           new Tile(26, 6));
        tileMap.put("bat",             new Tile(26, 8));
        tileMap.put("skeleton",        new Tile(29, 6));

        tileMap.put("player",          new Tile(29, 7));

        tileMap.put("letterY",         new Tile(30, 31));
        tileMap.put("letterO",         new Tile(20, 31));
        tileMap.put("letterU",         new Tile(26, 31));
        tileMap.put("letterW",         new Tile(28, 31));
        tileMap.put("letterI",         new Tile(27, 30));
        tileMap.put("letterN",         new Tile(19, 31));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
