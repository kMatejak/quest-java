package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.RandomUtils;

public class Ghost extends Actor{
    final int GHOST_STRENGTH = 2;
    final int INITIAL_HEALTH = 4;
    final int DECREASED_GHOST_STRENGTH = 2;

    public Ghost(Cell cell) {
        super(cell);
        this.health = INITIAL_HEALTH;
        this.strength = GHOST_STRENGTH;
    }

    @Override
    void attack(Actor actor) {
        if(this.health == INITIAL_HEALTH) {
            actor.decreaseHealth(GHOST_STRENGTH);
        } else {
            actor.decreaseHealth(DECREASED_GHOST_STRENGTH);
        }

        if(getHealth() < 0) {
            cell.deleteActor();
        }
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }


    @Override
    public String getTileName() {
        return "ghost";
    }

    //private boolean moveRight = true;

    @Override
    public void moveRandomly(int mapWidth, int mapHeight) {
        int x = RandomUtils.getRandomCoordinate(0, mapWidth);
        int y = RandomUtils.getRandomCoordinate(0, mapHeight);

        Cell nextCell = cell.getCellWithDefined(x, y);
        if (!isCellWithWall(nextCell) &&
                isCellWithClosedDoors(nextCell) &&   // zwraca zamiast true false
                !isEmptyCell(nextCell) &&
                isCellWithoutRedGate(nextCell) &&
                !isCellWithRocket(nextCell) &&
                isNoEmptyCell(nextCell)
        ) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else {
            moveRandomly(mapWidth, mapHeight);
        }
    }

    @Override
    public void move(int dx, int dy) { }
}

