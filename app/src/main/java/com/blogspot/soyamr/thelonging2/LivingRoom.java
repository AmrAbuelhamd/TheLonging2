package com.blogspot.soyamr.thelonging2;

import android.graphics.Bitmap;
import android.graphics.Point;

public class LivingRoom extends Room{
    //another elements
    //elements that only belongs to LivingRoom
    public LivingRoom(Bitmap roomBitmap, Controller controller,RoomParent roomParent) {
        super(new Point(1755,290), new Point(68,290), 300, 450 ,
                330, roomBitmap,controller, roomParent);
    }

}
