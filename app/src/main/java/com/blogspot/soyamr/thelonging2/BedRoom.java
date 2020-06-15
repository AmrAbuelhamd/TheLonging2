package com.blogspot.soyamr.thelonging2;

import android.graphics.Bitmap;
import android.graphics.Point;

class BedRoom extends Room {
    public BedRoom(Bitmap bedRoomBitmap, Controller controller,RoomParent roomParent) {
        super(new Point(1755, 290), new Point(68, 290), 300, 450,
                330, bedRoomBitmap, controller,roomParent);
    }

    @Override
    protected boolean hasReachedDoor(int x, int y) {
        return super.hasReachedDoor(x, y);

    }
}
