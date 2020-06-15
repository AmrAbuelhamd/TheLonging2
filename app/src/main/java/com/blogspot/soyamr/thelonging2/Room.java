package com.blogspot.soyamr.thelonging2;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Room {
    private Point rightDoor;
    private Point leftDoor;
    private int doorHeight;//in pixels
    private int floorYaxis;
    private int floorHeight;//in pixels
    private Bitmap roomBitmap;
    private RoomParent roomParent;
    private Controller controller;

    Room(Point rightDoor, Point leftDoor, int doorHeight, int floorYaxis, int floorHeight,
         Bitmap roomBitmap, Controller controller, RoomParent roomParent) {
        this.rightDoor = rightDoor;
        this.leftDoor = leftDoor;
        this.doorHeight = doorHeight;
        this.floorYaxis = floorYaxis;
        this.floorHeight = floorHeight;
        this.roomBitmap = roomBitmap;
        this.controller = controller;
        this.roomParent = roomParent;
    }

    protected boolean hasReachedDoor(int x, int y) {
        //check left door
        if (x <= leftDoor.x && y <= leftDoor.y + doorHeight && y >= leftDoor.y) {
            controller.changeBackground(roomParent.getBedRoom());
            controller.moveToTheRight();
            return true;
        }
        return false;
    }

    public Point getRightDoor() {
        return rightDoor;
    }

    public Point getLeftDoor() {
        return leftDoor;
    }

    public int getDoorHeight() {
        return doorHeight;
    }

    public int getFloorYaxis() {
        return floorYaxis;
    }

    public int getFloorHeight() {
        return floorHeight;
    }

    public Bitmap getRoomBitmap() {
        return roomBitmap;
    }
}
