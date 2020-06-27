package com.blogspot.soyamr.thelonging2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.blogspot.soyamr.thelonging2.elements.house.Room
import com.blogspot.soyamr.thelonging2.engine.GameSurface
import com.blogspot.soyamr.thelonging2.helpers.Utils
import com.blogspot.soyamr.thelonging2.helpers.Utils.appluScallingX
import com.blogspot.soyamr.thelonging2.helpers.Utils.appluScallingY
import com.example.kaushiknsanji.bookslibrary.BookSearchActivity


class MainActivity : AppCompatActivity(), ViewParent {
    private val time = 20L;
    private lateinit var scrollview: ScrollView
    private lateinit var textView2: TextView
    private lateinit var textView1: TextView
    lateinit var buttonGoBack: Button
    lateinit var gameSurface: GameSurface
    lateinit var backgroundImageView: ImageView
    lateinit var currentRoom: Room
    lateinit var rootLayout: RelativeLayout
    lateinit var buttonOpenLibirary: Button
    lateinit var parameterOpenLibirary: RelativeLayout.LayoutParams
    lateinit var fillParentLayout: RelativeLayout.LayoutParams
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Use a RelativeLayout to overlap both SurfaceView and ImageView
        fillParentLayout = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
        );
        rootLayout = RelativeLayout(this)
        rootLayout.layoutParams = fillParentLayout;

        showStartingScreen()

        setContentView(rootLayout)

    }

    private fun setTheScreen(editTextId: Int, buttonString: Int): Button {
        scrollview = ScrollView(this)
        rootLayout.addView(scrollview)
        val relativeLayout = RelativeLayout(this)
        scrollview.addView(relativeLayout, fillParentLayout)

        val lp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val font = ResourcesCompat.getFont(this, R.font.new_font)
        val editText = EditText(this)
        editText.setText(editTextId)
        editText.setFocusable(false);
        editText.setClickable(false);
        editText.setTypeface(font, Typeface.BOLD)
        editText.textSize = 20f
        editText.setTextColor(Color.WHITE)
        editText.setId(View.generateViewId());

        relativeLayout.addView(editText)
        relativeLayout.setBackgroundColor(Color.BLACK)
        relativeLayout.setId(View.generateViewId());


        val button = Button(this)
        button.text = resources.getText(buttonString)
        button.setTypeface(font, Typeface.BOLD)

        lp.addRule(RelativeLayout.BELOW, editText.getId());
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL)
        relativeLayout.addView(button, lp)
        return button;
    }

    private fun showStartingScreen() {
        val button = setTheScreen(R.string.game_story, R.string.start)
        button.setOnClickListener {
            rootLayout.removeView(scrollview)
            startGame()

        }
    }

    private fun showEndingScreen() {
        this@MainActivity.runOnUiThread {
            gameSurface.pause()
            rootLayout.removeView(gameSurface)
            val button = setTheScreen(R.string.game_ending, R.string.end)
            button.setOnClickListener {
                rootLayout.removeView(scrollview)
                finish()
            }
        }
    }

    private fun startTimer() {
        object : CountDownTimer(60 * 60 * time, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val second = millisUntilFinished % (60 * 1000) / 1000
                val minute = millisUntilFinished % (60 * 60 * 1000) / (60 * 1000)
                val hour = millisUntilFinished % (24 * 60 * 60 * 1000) / (60 * 60 * 1000)
                val day = millisUntilFinished / (24 * 60 * 60 * 1000)
                textView1.text = "${day}D:${hour}H:${minute}M:${second}S"
                textView2.text = "${day}:${hour}:${minute}:${second}"
            }

            override fun onFinish() {
                showEndingScreen()
            }
        }.start()
    }

    private fun startGame() {
        firstTime = false;
        setScallingFactor()
        gameSurface = GameSurface(this)
        gameSurface.setZOrderOnTop(true)
        gameSurface.getHolder().setFormat(PixelFormat.TRANSPARENT);

        backgroundImageView = ImageView(this)
        backgroundImageView.scaleType = ImageView.ScaleType.FIT_XY

        rootLayout.addView(gameSurface, fillParentLayout);
        rootLayout.addView(backgroundImageView, fillParentLayout);

        initalizeButtons()
        initalizeTimer()
        startTimer()
    }

    private fun initalizeTimer() {
        val font = ResourcesCompat.getFont(this, R.font.new_font)
        textView1 = TextView(this)
        textView2 = TextView(this)
        textView1.setTypeface(font, Typeface.BOLD)
        textView2.setTypeface(font, Typeface.BOLD)
        textView1.textSize = 20f
        textView2.textSize = 20f
        textView1.setTextColor(Color.BLACK)
        textView2.setTextColor(Color.BLACK)
        val parameterTimerTV1 = RelativeLayout
            .LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
        parameterTimerTV1.leftMargin = appluScallingX(appluScallingX(145))
        parameterTimerTV1.topMargin = appluScallingY(appluScallingY(50))

        val parameterTimerTV2 = RelativeLayout
            .LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
        parameterTimerTV2.leftMargin = appluScallingX(appluScallingX(145))
        parameterTimerTV2.topMargin = appluScallingY(appluScallingY(125))

        rootLayout.addView(textView1, parameterTimerTV1)
        rootLayout.addView(textView2, parameterTimerTV2)
    }


    private fun initalizeButtons() {
        buttonOpenLibirary = Button(this)
        buttonOpenLibirary.tag = "openLibraryButtonView"
//        buttonOpenLibirary.text = "open the library"
        buttonOpenLibirary.background = getDrawable(R.drawable.openbook)
        buttonOpenLibirary.setOnClickListener {
            val intent = Intent(this, BookSearchActivity::class.java)
            removeButtonOpenShelf();
            startActivity(intent)
        }

        parameterOpenLibirary = RelativeLayout
            .LayoutParams(appluScallingX(200), appluScallingY(124))
        parameterOpenLibirary.leftMargin = appluScallingX(1883)
        parameterOpenLibirary.topMargin = appluScallingY(223)


        buttonGoBack = Button(this)
        buttonGoBack.tag = "goBackButtonView"
        buttonGoBack.background = getDrawable(R.drawable.goback)
        buttonGoBack.setOnClickListener {
            gameSurface.changeBackground(currentRoom.nextRoom)
            removeButtonGoBack()
            gameSurface.moveToTheLeft()
        }
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
            if (rootLayout.findViewWithTag<Button>("openLibraryButtonView") == null)
                rootLayout.addView(buttonOpenLibirary, parameterOpenLibirary)
            gameSurface.buttonIsShown = true;
        }
    }

    override fun removeButtonOpenShelf() {
        this@MainActivity.runOnUiThread {
            rootLayout.removeView(buttonOpenLibirary)
            gameSurface.buttonIsShown = false;
        }
    }

    override fun addButtonGoBack() {
        this@MainActivity.runOnUiThread {
            if (rootLayout.findViewWithTag<Button>("goBackButtonView") == null)
                rootLayout.addView(buttonGoBack, parameterOpenLibirary)
            gameSurface.buttonIsShown = true;
        }
    }

    override fun removeButtonGoBack() {
        this@MainActivity.runOnUiThread {
            rootLayout.removeView(buttonGoBack)
            gameSurface.buttonIsShown = false;
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
            removeButtonGoBack();
            removeButtonGoBack();
        }
    }

    override fun getContext(): Context {
        return this;
    }

    override fun onPause() {
        super.onPause()
        if (!firstTime) {
            gameSurface.pause()
        }
    }

    var firstTime = true
    override fun onResume() {
        super.onResume()
        if (!firstTime) {
            gameSurface.resume()
        }

    }
}
