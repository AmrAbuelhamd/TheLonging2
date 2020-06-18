package com.blogspot.soyamr.thelonging2.engine;

import com.blogspot.soyamr.thelonging2.house.Room;

public interface Controller {
    void changeBackground(Room room);
    void hasReachedDoor(int x, int y);
    void moveToTheRight();
    void moveToTheLeft();
}
