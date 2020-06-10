package com.blogspot.soyamr.thelonging2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;

    private final List<VovaCharacter> vovaList = new ArrayList<>();
    private final List<Explosion> explosionList = new ArrayList<>();

    private static final int MAX_STREAMS = 100;
    private int soundIdExplosion;
    private int soundIdBackground;

    private boolean soundPoolLoaded;
    private SoundPool soundPool;


    public GameSurface(Context context) {
        super(context);

        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);
        holder = getHolder();
        // Sét callback.
        this.getHolder().addCallback(this);

        this.initSoundPool();
        this.initChibiCharacter();
    }

    private void initChibiCharacter() {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi1);
        VovaCharacter chibi1 = new VovaCharacter(this, chibiBitmap1, 100, 50);

//        Bitmap chibiBitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi2);
//        ChibiCharacter chibi2 = new ChibiCharacter(this, chibiBitmap2, 300, 150);

        this.vovaList.add(chibi1);
//        this.chibiList.add(chibi2);
    }

    private void initSoundPool() {

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

        this.soundPool = builder.build();


        // When SoundPool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolLoaded = true;

                // Playing background sound.
                playSoundBackground();
            }
        });

        // Load the sound background.mp3 into SoundPool
        this.soundIdBackground = this.soundPool.load(this.getContext(), R.raw.background, 1);

        // Load the sound explosion.wav into SoundPool
        this.soundIdExplosion = this.soundPool.load(this.getContext(), R.raw.explosion, 1);
    }

    public void playSoundExplosion() {
        if (this.soundPoolLoaded) {
            float leftVolumn = 0.8f;
            float rightVolumn = 0.8f;
            // Play sound explosion.wav
            int streamId = this.soundPool.play(this.soundIdExplosion, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundBackground() {
        if (this.soundPoolLoaded) {
            float leftVolumn = 0.8f;
            float rightVolumn = 0.8f;
            // Play sound background.mp3
            int streamId = this.soundPool.play(this.soundIdBackground, leftVolumn, rightVolumn, 1, -1, 1f);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            Iterator<VovaCharacter> iterator = this.vovaList.iterator();


            while (iterator.hasNext()) {
                VovaCharacter chibi = iterator.next();
                if (chibi.getX() < x && x < chibi.getX() + chibi.getCharacterWidth()
                        && chibi.getY() < y && y < chibi.getY() + chibi.getCharacterHeight()) {
                    // Remove the current element from the iterator and the list.
                    iterator.remove();

                    // Create Explosion object.
                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.explosion);
                    Explosion explosion = new Explosion(this, bitmap, chibi.getX(), chibi.getY());

                    this.explosionList.add(explosion);
                }
            }


            for (VovaCharacter chibi : vovaList) {
                int movingVectorX = x - chibi.getX();
                int movingVectorY = y - chibi.getY();
                chibi.setMovingVector(movingVectorX, movingVectorY);
            }
            return true;
        }
        return false;
    }

    public void update() {
        for (VovaCharacter chibi : vovaList) {
            chibi.update();
        }
        for (Explosion explosion : this.explosionList) {
            explosion.update();
        }

        Iterator<Explosion> iterator = this.explosionList.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();

            if (explosion.isFinish()) {
                // If explosion finish, Remove the current element from the iterator & list.
                iterator.remove();
                continue;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (VovaCharacter chibi : vovaList) {
            chibi.draw(canvas);
        }

        for (Explosion explosion : this.explosionList) {
            explosion.draw(canvas);
        }

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        resume();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        pause();
    }

    void pause() {
        soundPool.autoPause();
        this.gameThread.setRunning(false);
        while (true) {
            // Parent thread must wait until the end of GameThread.
            try {
                this.gameThread.join();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    SurfaceHolder holder;

    void resume() {
        soundPool.autoResume();
        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }
}