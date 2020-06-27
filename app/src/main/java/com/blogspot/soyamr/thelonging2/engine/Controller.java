package com.blogspot.soyamr.thelonging2.engine;

import com.blogspot.soyamr.thelonging2.elements.house.Room;

public interface Controller {
    void changeBackground(Room room);
    void hasReachedDoor(int x, int y);
    void moveToTheRight();
    void moveToTheLeft();
    boolean steppingOnRoomObject(int x, int y);
    void addLibraryOpenButton();
    void whereAmI(int x, int y);

    boolean reachedFloorEnd(int y);
    int getCurrentFloorY();
}
