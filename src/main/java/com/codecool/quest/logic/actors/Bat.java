package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Bat extends Actor {
    final int BAT_STRENGTH = 10;
    final int INITIAL_HEALTH = 5;
    final int DECREASED_BAT_STRENGTH = 3;

    private boolean moveRight = true;

    public Bat(Cell cell) {
        super(cell);
        this.health = INITIAL_HEALTH;
        this.strength = BAT_STRENGTH;
    }

    @Override
    void attack(Actor actor) {
        if (this.health == INITIAL_HEALTH) {
            actor.decreaseHealth(BAT_STRENGTH);
        } else {
            actor.decreaseHealth(DECREASED_BAT_STRENGTH);
        }

        if (getHealth() < 0) {
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
        return "bat";
    }

    private void moveToNextCell(int x, int y) {
        Cell nextCell = cell.getNeighbor(x, y);
        if (!isCellWithWall(nextCell) &&
                isCellWithClosedDoors(nextCell) &&
                !isEmptyCell(nextCell) &&
                isNoEmptyCell(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else {
            moveRight = !moveRight;
        }
    }


    @Override
    public void move(int dx, int dy) {
        if (moveRight) {
            this.moveToNextCell(dx, dy);
        } else {
            dy = -dy;
            this.moveToNextCell(dx, dy);
        }
    }
    @Override
    public void moveRandomly(int mapWidth, int mapHeight) {}
}
