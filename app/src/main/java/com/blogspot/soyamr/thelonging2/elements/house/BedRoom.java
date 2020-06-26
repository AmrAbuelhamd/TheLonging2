package com.blogspot.soyamr.thelonging2.elements.house;

import android.graphics.Bitmap;

import com.blogspot.soyamr.thelonging2.engine.Controller;
import com.blogspot.soyamr.thelonging2.helpers.Point;
import com.blogspot.soyamr.thelonging2.helpers.RayCastingAlgorithm;

class BedRoom extends Room {

    //left door
    static Point[] leftDoor = {
            new Point(0, 199, true),
            new Point(82, 237, true),
            new Point(82, 771, true),
            new Point(0, 844, true),
    };
    //right door
    static Point[] rightDoor = {
            new Point(2260, 212, true),
            new Point(2188, 247, true),
            new Point(2188, 788, true),
            new Point(2260, 855, true),
    };

    //floor
    static Point[] floor = {
            new Point(2255, 861, true),
            new Point(2190, 805, true),
            new Point(2020, 840, true),
            new Point(2020, 885, true),
            new Point(1874, 931, true),
            new Point(1742, 861, true),
            new Point(1771, 840, true),
            new Point(1491, 840, true),
            new Point(1491, 825, true),
            new Point(1334, 840, true),
            new Point(1334, 802, true),
            new Point(137, 802, true),
            new Point(101, 753, true),
            new Point(0, 825, true),
            new Point(0, 1080, true),
            new Point(2260, 1080, true),
    };
    static Point[] bigChair = {
            new Point(1894, 600, true),
            new Point(1750, 858, true),
            new Point(1867, 923, true),
            new Point(2015, 882, true),
            new Point(2015, 600, true)
    };

    static Point[] bookShelf = {
            new Point(1553, 176, true),
            new Point(1851, 176, true),
            new Point(1851, 630, true),
            new Point(1786, 722, true),
            new Point(1786, 802, true),
            new Point(1553, 802, true)
    };

    private RoomParent roomParent;
    private Controller controller;

    public BedRoom(Bitmap bedRoomBitmap, Controller controller, RoomParent roomParent) {
        super(rightDoor, leftDoor, floor, bedRoomBitmap);
        this.controller = controller;
        this.roomParent = roomParent;
    }

    @Override
    public boolean isInsideFloor(int x, int y) {
        return super.isInsideFloor(x, y);
    }

    public void hasReachedDoor(int x, int y) {
        if (hasReachedLeftDoor(x, y)) {
            controller.changeBackground(roomParent.getLivingRoom());
            controller.moveToTheRight();
        } else if (hasReachedRightDoor(x, y)) {
//            controller.changeBackground(roomParent.getLivingRoom());
//            controller.moveToTheLeft();
        }
    }

    @Override
    public boolean isSteppingOnRoomObject(int x, int y) {
        return (RayCastingAlgorithm.isInside(bigChair, new Point(x, y)));
    }

    @Override
    public int whereAmI(int x, int y) {
        if (RayCastingAlgorithm.isInside(bookShelf, new Point(x, y)))
            return Room.LIBRARY;
        else
            return -1;
    }
}
