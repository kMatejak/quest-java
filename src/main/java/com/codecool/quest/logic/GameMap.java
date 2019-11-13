package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.*;
import com.codecool.quest.logic.items.Item;

import java.util.ArrayList;
import java.util.List;


public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private static List<Actor> actors = new ArrayList<>();


    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0) {
            return cells[x + 1][y];
        } else if (y < 0) {
            return cells[x][y + 1];
        } else if (x > this.getWidth() - 1) {
            return cells[x - 1][y];
        } else if (y > this.getHeight() - 1) {
            return cells[x][y - 1];
        } else {
            return cells[x][y];
        }
    }

    public void setActor(Actor actor) {
        actors.add(actor);
    }


    public Actor getActor(String name) {
        for (Actor actor : actors) {
            if (actor.getTileName().equals(name)) {
                return actor;
            }
        }
        return null;
    }


    public void moveMonsters() {
//        for (Actor actor : actors) {
//            actor.moveRandomly(...);
//        }
    }

    public void removeActor(String name) {
        actors.remove(getActor(name));
    }

    public void pickUpItem() {
        Item item = getActor("player").getCell().getItem(); // getPlayer().getCell().getItem();
        if (item != null) {
            item.onPickUp();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
