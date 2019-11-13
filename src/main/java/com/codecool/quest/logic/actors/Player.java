package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

import static com.codecool.quest.logic.items.Sword.swordNum;


public class Player extends Actor {
    final static int INITIAL_HEALTH = 10;
    final static int INITIAL_STRENGTH = 5;
    final static int INCREASED_STRENGTH = 12;

    public Player(Cell cell) {

        super(cell);
        this.health = INITIAL_HEALTH;
        this.strength = INITIAL_STRENGTH;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        // did player win?
        if (isCellWithRocket(nextCell) && hasRedKey())
        {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            finishGame();

        }
        // flames
        else if (doesFallIntoFlames(nextCell))
        {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            decreaseHealth(6);
        }
//        else if (isCellWithClosedDoors(nextCell) && canOpenDoor(nextCell)) {
//            openDoor(nextCell);
//            cell.setActor(null);
//            nextCell.setActor(this);
//            cell = nextCell;
//        }
        else if (canEnter(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        // attack
        else if (nextCell.getActor() != null) {
            Actor enemy = nextCell.getActor();
            this.attack(enemy);
            if (enemy.getHealth() >= 0) {
                enemy.attack(this);
            }
        }
    }

    private boolean canEnter(Cell nextCell) {
        return !super.isCellWithWall(nextCell) &&
                !isCellWithActor(nextCell) &&
                isCellWithClosedDoors(nextCell) && // zwraca true zamiast false
                isCellWithoutRedGate(nextCell) &&
                !isEmptyCell(nextCell) &&
                isNoEmptyCell(nextCell);
    }

    @Override
    public void attack(Actor monster) {
        if (swordNum > 0) {
            monster.decreaseHealth(INCREASED_STRENGTH);
        } else {
            monster.decreaseHealth(INITIAL_STRENGTH);
        }
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getInitialHealth() { return INITIAL_HEALTH; }

    public String getTileName() {
        return "player";
    }

    public void moveRandomly(int mapWidth, int mapHeight) {}
}
