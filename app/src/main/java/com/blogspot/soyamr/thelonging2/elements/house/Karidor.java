package com.blogspot.soyamr.thelonging2.elements.house;

import android.graphics.Bitmap;

import com.blogspot.soyamr.thelonging2.elements.character.VovaCharacter;
import com.blogspot.soyamr.thelonging2.engine.Controller;
import com.blogspot.soyamr.thelonging2.helpers.Point;
import com.blogspot.soyamr.thelonging2.helpers.RayCastingAlgorithm;
import com.blogspot.soyamr.thelonging2.helpers.Utils;

import static com.blogspot.soyamr.thelonging2.helpers.Utils.BOTTOM_TO_TOP;

public class Karidor extends Room {

    //left door
    static Point[] leftDoor = {
            new Point(0, 199, true),
            new Point(82, 237, true),
            new Point(82, 771, true),
            new Point(0, 844, true),
    };


    static Point[] kitchenDoor = {
            new Point(340, 200, true),
            new Point(620, 200, true),
            new Point(662, 783, true),
            new Point(345, 781, true),
    };

    //floor
    static Point[] floor = {
            new Point(2255, 861, true),
            new Point(2190, 805, true),
            new Point(2020, 745, true),
            new Point(2020, 885, true),
            new Point(1874, 931, true),
            new Point(1742, 861, true),
            new Point(1771, 745, true),
            new Point(1491, 745, true),
            new Point(1491, 825, true),
            new Point(1334, 745, true),
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
    public Karidor(Bitmap roomBitmap, Controller controller, RoomParent roomParent) {
        super(null, leftDoor, floor, roomBitmap, Utils.appluScallingY(745));
        this.controller = controller;
        this.roomParent = roomParent;
    }

    public void hasReachedDoor(int x, int y) {
        if (hasReachedLeftDoor(x, y)) {
            controller.changeBackground(roomParent.getBedRoom());
            controller.moveToTheRight();
        } else if (RayCastingAlgorithm.isInside(kitchenDoor, new Point(x, y))
                && VovaCharacter.DIRECTION == BOTTOM_TO_TOP) {
            controller.changeBackground(roomParent.getKitchen());
            controller.moveToTheRight();
        }
    }

    @Override
    public boolean isSteppingOnRoomObject(int x, int y) {
        return false;
    }

    @Override
    public int whereAmI(int x, int y) {
        return -1;
    }

    @Override
    public Room getNextRoom() {
        return null;
    }
}
