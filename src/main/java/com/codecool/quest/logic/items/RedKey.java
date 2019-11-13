package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class RedKey extends Item {

    public static int redKeyNum = 0;

    public RedKey(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "redKey";
    }

    @Override
    public int getNum() {
        return redKeyNum;
    }

    @Override
    public void onPickUp() {
        redKeyNum++;
        cell.deleteItem();
    }
}