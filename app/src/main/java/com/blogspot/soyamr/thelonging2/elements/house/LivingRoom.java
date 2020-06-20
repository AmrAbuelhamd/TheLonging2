package com.blogspot.soyamr.thelonging2.elements.house;

import android.graphics.Bitmap;

import com.blogspot.soyamr.thelonging2.engine.Controller;
import com.blogspot.soyamr.thelonging2.helpers.Point;

public class LivingRoom extends Room{

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


    private RoomParent roomParent;
    private Controller controller;
    //another elements
    //elements that only belongs to LivingRoom
    public LivingRoom(Bitmap roomBitmap, Controller controller, RoomParent roomParent) {
        super(rightDoor, leftDoor, floor, roomBitmap);
        this.controller = controller;
        this.roomParent = roomParent;
    }

    public void hasReachedDoor(int x, int y) {
        if (hasReachedLeftDoor(x, y)) {

        } else if (hasReachedRightDoor(x, y)) {
            controller.changeBackground(roomParent.getBedRoom());
            controller.moveToTheLeft();
        }
    }

    @Override
    public boolean isSteppingOnRoomObject(int x, int y) {
        return false;
    }
}
