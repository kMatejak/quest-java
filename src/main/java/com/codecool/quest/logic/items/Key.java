package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Key extends Item {

    public static int keyNum = 0;

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }

    @Override
    public int getNum() {
        return keyNum;
    }

    @Override
    public void onPickUp() {
        keyNum++;
        cell.deleteItem();
    }
}
