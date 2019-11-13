package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Sword extends Item {

    public static int swordNum = 0;

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    @Override
    public int getNum() { return swordNum; }

    @Override
    public void onPickUp() {
        swordNum++;
        cell.deleteItem();

    }
}
