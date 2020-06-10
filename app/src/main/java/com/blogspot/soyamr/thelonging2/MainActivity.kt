package com.blogspot.soyamr.thelonging2

import android.media.SoundPool
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var gameSurface:GameSurface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
         gameSurface = GameSurface(this)
        setContentView(gameSurface)


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
