package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Doors;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.RedKey;


public abstract class Actor implements Drawable {
    public Cell cell;
    protected int health;
    int strength;
    public static boolean canFinishGame;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public abstract void move(int dx, int dy);

    boolean isCellWithWall(Cell nextCell) {
        String wall = CellType.WALL.getTileName();
        return wall.equals(nextCell.getTileName());
    }

    boolean isCellWithActor(Cell nextCell) {
        return nextCell.getActor() != null;
    }

    abstract void attack(Actor actor);

    public abstract int getHealth();

    public abstract int getInitialHealth();

    boolean isCellWithClosedDoors(Cell nextCell) {
        String closedDoors = CellType.CLOSED_DOORS.getTileName();
        boolean isClosedDoorsNextCell = closedDoors.equals(nextCell.getTileName());
        boolean hasKey = Key.keyNum > 0;
        boolean canOpenDoors = hasKey && isClosedDoorsNextCell;

        if(canOpenDoors) {
            nextCell.setType(CellType.OPENED_DOORS);
            // Doors.setDoorsOpen(true);
        }
        return !isClosedDoorsNextCell;
    }

    boolean isCellWithoutRedGate(Cell nextCell) {
        String closedRedGate = CellType.RED_GATE.getTileName();
        boolean isRedGateNextCell = closedRedGate.equals(nextCell.getTileName());
        boolean hasRedKey = RedKey.redKeyNum > 0;
        boolean canOpenRedGate = hasRedKey && isRedGateNextCell;

        if(canOpenRedGate) {
            nextCell.setType(CellType.RED_PLATFORM);
        }
        return !isRedGateNextCell;
    }

    boolean isCellWithRocket(Cell nextCell) {
        String rocket = CellType.ROCKET2.getTileName();
        return rocket.equals(nextCell.getTileName());
    }

    boolean hasRedKey() {
        return RedKey.redKeyNum > 0;
    }

    boolean isEmptyCell(Cell nextCell) {
        String emptyCell = CellType.EMPTY.getTileName();
        return emptyCell.equals(nextCell.getTileName());
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    boolean isNoEmptyCell(Cell nextCell) {
        String emptyCell = CellType.EMPTY.getTileName();
        return !emptyCell.equals(nextCell.getTileName());
    }

    boolean doesFallIntoFlames(Cell nextCell) {
        String flames = CellType.FIRE.getTileName();
        return flames.equals(nextCell.getTileName());
    }

    void finishGame() {
        canFinishGame = true;
    }

    void decreaseHealth(int attack) {
        health -= attack;
        if (health < 0) {
            cell.deleteActor();
        }
    }

    public abstract void moveRandomly(int mapWidth, int mapHeight);
}
