package com.blogspot.soyamr.thelonging2.elements.house;

import android.graphics.Bitmap;

import com.blogspot.soyamr.thelonging2.helpers.Point;
import com.blogspot.soyamr.thelonging2.helpers.RayCastingAlgorithm;
import com.blogspot.soyamr.thelonging2.helpers.Utils;

abstract public class Room {
    private Point[] rightDoor;
    private Point[] leftDoor;
    private Point[] floor;
    private Bitmap roomBitmap;

    Room(Point[] rightDoor, Point[] leftDoor, Point[] floor,
         Bitmap roomBitmap) {

        this.rightDoor = rightDoor;
        this.leftDoor = leftDoor;
        this.floor = floor;
        this.roomBitmap = roomBitmap;

    }

    public boolean isInsideFloor(int x, int y) {
        return RayCastingAlgorithm.isInside(floor, new Point(x, y));
    }

    public Point[] getRightDoor() {
        return rightDoor;
    }

    public Point[] getLeftDoor() {
        return leftDoor;
    }

    public Point[] getFloor() {
        return floor;
    }

    public Bitmap getRoomBitmap() {
        return roomBitmap;
    }

    boolean hasReachedRightDoor(int x, int y) {
        return RayCastingAlgorithm.isInside(rightDoor, new Point(x + Utils.characterWidth, y));
    }

    boolean hasReachedLeftDoor(int x, int y) {
        return RayCastingAlgorithm.isInside(leftDoor, new Point(x, y));
    }

    //as if abstract
    public void hasReachedDoor(int x, int y) {
    }

    public abstract boolean isSteppingOnRoomObject(int x, int y);
}
