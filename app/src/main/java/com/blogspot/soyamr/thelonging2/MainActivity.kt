package com.blogspot.soyamr.thelonging2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(),ViewParent {
    lateinit var gameSurface: GameSurface
    lateinit var backgroundImageView: ImageView
    lateinit var currentRoom: Room
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );



        gameSurface = GameSurface(this)
        gameSurface.setZOrderOnTop(true)
        gameSurface.getHolder().setFormat(PixelFormat.TRANSPARENT);


        backgroundImageView = ImageView(this)
        // Use a RelativeLayout to overlap both SurfaceView and ImageView
        val fillParentLayout: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
        );
        val rootLayout = RelativeLayout(this);
        rootLayout.layoutParams = fillParentLayout;
        rootLayout.addView(gameSurface, fillParentLayout);
        rootLayout.addView(backgroundImageView, fillParentLayout);

        setContentView(rootLayout)

    }


    override fun changeBackground(room: Room) {
        this@MainActivity.runOnUiThread {
            backgroundImageView.setImageBitmap(room.roomBitmap);
            currentRoom = room
        }
    }

    override fun getContext(): Context {
        return this;
    }

    override fun onPause() {
        super.onPause()
        gameSurface.pause()
    }

    override fun onResume() {
        super.onResume()
        gameSurface.resume()
    }
}
