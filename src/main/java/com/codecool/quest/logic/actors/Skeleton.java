package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Skeleton extends Actor {
    final int SKELETON_STRENGTH = 5;
    final int INITIAL_HEALTH = 10;
    final int DECREASED_SKELETON_STRENGTH = 2;

    public Skeleton(Cell cell) {
        super(cell);
        this.health = INITIAL_HEALTH;
        this.strength = SKELETON_STRENGTH;
    }

    @Override
    void attack(Actor actor) {
        if(this.health == INITIAL_HEALTH) {
            actor.decreaseHealth(SKELETON_STRENGTH);
        } else {
            actor.decreaseHealth(DECREASED_SKELETON_STRENGTH);
        }

        if(getHealth() < 0) {
            cell.deleteActor();
        }
    }

    protected int getAttackStrength() {
        if(this.health == INITIAL_HEALTH) {
            return (SKELETON_STRENGTH);
        } else {
            return (DECREASED_SKELETON_STRENGTH);
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
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {}

    @Override
    public void moveRandomly(int mapWidth, int mapHeight) {}
}