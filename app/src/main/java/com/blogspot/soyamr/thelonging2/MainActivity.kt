package com.blogspot.soyamr.thelonging2

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.thelonging2.bookshelf.LibraryShelf
import com.blogspot.soyamr.thelonging2.elements.house.Room
import com.blogspot.soyamr.thelonging2.engine.GameSurface
import com.blogspot.soyamr.thelonging2.helpers.Utils
import com.blogspot.soyamr.thelonging2.helpers.Utils.appluScallingX
import com.blogspot.soyamr.thelonging2.helpers.Utils.appluScallingY


class MainActivity : AppCompatActivity(),ViewParent {
    lateinit var gameSurface: GameSurface
    lateinit var backgroundImageView: ImageView
    lateinit var currentRoom: Room
    lateinit var rootLayout: RelativeLayout
    lateinit var buttonOpenLibirary: Button
    lateinit var parameterOpenLibirary: RelativeLayout.LayoutParams
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setScallingFactor()
        gameSurface = GameSurface(this)
        gameSurface.setZOrderOnTop(true)
        gameSurface.getHolder().setFormat(PixelFormat.TRANSPARENT);

        backgroundImageView = ImageView(this)
        backgroundImageView.scaleType = ImageView.ScaleType.FIT_XY
        // Use a RelativeLayout to overlap both SurfaceView and ImageView
        val fillParentLayout: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
        );
        rootLayout = RelativeLayout(this)
        rootLayout.layoutParams = fillParentLayout;
        rootLayout.addView(gameSurface, fillParentLayout);
        rootLayout.addView(backgroundImageView, fillParentLayout);

        setContentView(rootLayout)

        initalizeButtons()
    }

    private fun initalizeButtons() {
        buttonOpenLibirary = Button(this)
        buttonOpenLibirary.text = "open the library"
        buttonOpenLibirary.background = getDrawable(R.drawable.open_library)
        buttonOpenLibirary.setOnClickListener {
            openLibraryActivity()
        }
        parameterOpenLibirary = RelativeLayout
            .LayoutParams(appluScallingX(200), appluScallingY(124))
        parameterOpenLibirary.leftMargin= appluScallingX(1883)
        parameterOpenLibirary.topMargin= appluScallingY(223)
    }

    private fun openLibraryActivity() {
        val intent = Intent(this, LibraryShelf::class.java)
        startActivity(intent)
    }


    private fun setScallingFactor() {
        val xy = getScreenMetrics();
        Utils.setXScalingFactor(xy.first, xy.second)
    }

    override fun getRoomBitmap(roomID: Int): Bitmap? {
        return BitmapFactory.decodeResource(this.resources, roomID)
    }

    override fun addButtonBookShelf() {
        this@MainActivity.runOnUiThread {
            rootLayout.addView(buttonOpenLibirary, parameterOpenLibirary)
        }
    }

    override fun removeButtonOpenShelf() {
        this@MainActivity.runOnUiThread {
            rootLayout.removeView(buttonOpenLibirary)
        }
    }

    private fun getScreenMetrics(): Pair<Int, Int> {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        return Pair(height, width)
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
