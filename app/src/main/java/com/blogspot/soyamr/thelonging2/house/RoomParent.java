package com.blogspot.soyamr.thelonging2.house;

import android.graphics.Bitmap;

import com.blogspot.soyamr.thelonging2.engine.Controller;

public class RoomParent {
    private LivingRoom livingRoom;
    private BedRoom bedRoom;

    public RoomParent(Bitmap livingRoomBitmap, Bitmap bedRoomBitmap, Controller controller) {
        livingRoom = new LivingRoom(livingRoomBitmap, controller, this);
        bedRoom = new BedRoom(bedRoomBitmap, controller, this);
    }

    public BedRoom getBedRoom() {
        return bedRoom;
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }
}
