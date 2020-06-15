package com.blogspot.soyamr.thelonging2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, Controller {

    private GameThread gameThread;

    private final List<VovaCharacter> vovaList = new ArrayList<>();
    private final List<Explosion> explosionList = new ArrayList<>();

    Room currentRoom;
    private RoomParent roomParent;

    private static final int MAX_STREAMS = 100;
    private int soundIdExplosion;
    private int soundIdBackground;

    private boolean soundPoolLoaded;
    private SoundPool soundPool;
    private ViewParent refToParent;

    public GameSurface(ViewParent object) {
        super(object.getContext());
        refToParent = object;

        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);
        holder = getHolder();
        // Sét callback.
        this.getHolder().addCallback(this);

        this.initSoundPool();
        this.initVovaCharacter();

        roomParent =
                new RoomParent(getRoomBitmap(R.drawable.background2),
                        getRoomBitmap(R.drawable.room), this);
        currentRoom = roomParent.getLivingRoom();

    }

    private Bitmap getRoomBitmap(int roomID) {
        return BitmapFactory.decodeResource(this.getResources(), roomID);
    }


    private void initVovaCharacter() {
        Bitmap vova = BitmapFactory.decodeResource(this.getResources(), R.drawable.vova);
        VovaCharacter chibi1 = new VovaCharacter(this, vova, 100, 50);

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
//            Log.e("amr","y "+y);
//            if (y < 600)
//                return false;

//            Iterator<VovaCharacter> iterator = this.vovaList.iterator();
//            while (iterator.hasNext()) {
//                VovaCharacter chibi = iterator.next();
//                if (chibi.getX() < x && x < chibi.getX() + chibi.getCharacterWidth()
//                        && chibi.getY() < y && y < chibi.getY() + chibi.getCharacterHeight()) {
//                    // Remove the current element from the iterator and the list.
//                    iterator.remove();
//
//                    // Create Explosion object.
//                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.explosion);
//                    Explosion explosion = new Explosion(this, bitmap, chibi.getX(), chibi.getY());
//
//                    this.explosionList.add(explosion);
//                }
//            }


            for (VovaCharacter vova : vovaList) {
                //now the character foot will follow my touch instead of its head
                int movingVectorX = x - vova.getCharacterWidth() - vova.getX();
                int movingVectorY = y - vova.getCharacterHeight() - vova.getY();
                vova.setMovingVector(movingVectorX, movingVectorY);
            }
            return true;
        }
        return false;
    }

    public void changeBackground(Room room) {
        currentRoom = room;
        refToParent.changeBackground(room);
    }

    @Override
    public void moveToTheRight() {
        vovaList.get(0).moveToTheRightOfScreen();
    }

    @Override
    public void moveToTheLeft() {
        vovaList.get(0).moveToTheLeftOfScreen();
    }

    public void update() {
        for (VovaCharacter vova : vovaList) {
            vova.update();
        }
        for (Explosion explosion : this.explosionList) {
            explosion.update();
        }
        currentRoom.hasReachedDoor(
                vovaList.get(0).x,
                vovaList.get(0).y);


//        Iterator<Explosion> iterator = this.explosionList.iterator();
//        while (iterator.hasNext()) {
//            Explosion explosion = iterator.next();
//
//            if (explosion.isFinish()) {
//                // If explosion finish, Remove the current element from the iterator & list.
//                iterator.remove();
//                continue;
//            }
//        }


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (VovaCharacter vova : vovaList) {
//            currentRoom.hasReachedDoor(vova);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            vova.draw(canvas);
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
        changeBackground(currentRoom);
    }
}