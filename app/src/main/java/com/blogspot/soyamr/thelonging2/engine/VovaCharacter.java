package com.blogspot.soyamr.thelonging2.engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.blogspot.soyamr.thelonging2.helpers.Utils;

import static com.blogspot.soyamr.thelonging2.helpers.Utils.BOTTOM_TO_TOP;
import static com.blogspot.soyamr.thelonging2.helpers.Utils.FLOOR_Y_END;
import static com.blogspot.soyamr.thelonging2.helpers.Utils.INITIAL_LEFT_POSITION;
import static com.blogspot.soyamr.thelonging2.helpers.Utils.LEFT_TO_RIGHT;
import static com.blogspot.soyamr.thelonging2.helpers.Utils.RIGHT_TO_LEFT;
import static com.blogspot.soyamr.thelonging2.helpers.Utils.TOP_TO_BOTTOM;

public class VovaCharacter extends GameObject {


    // Row index of Image are being used.
    private int rowUsing = LEFT_TO_RIGHT;

    private int colUsing;

    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;
    private Bitmap[] topToBottoms;
    private Bitmap[] bottomToTops;

    // Velocity of game character (pixel/millisecond)
    public static final float VELOCITY = 0.9f;

    private int movingVectorX = 0;
    private int movingVectorY = 0;

    private long lastDrawNanoTime = -1;

    private Controller controller;

    public VovaCharacter(Bitmap image, int x, int y, Controller controller) {
        super(image, 4, 3, x, y);

        this.controller = controller;
        this.topToBottoms = new Bitmap[colCount]; // 3
        this.rightToLefts = new Bitmap[colCount]; // 3
        this.leftToRights = new Bitmap[colCount]; // 3
        this.bottomToTops = new Bitmap[colCount]; // 3

        for (int col = 0; col < this.colCount; col++) {
            this.topToBottoms[col] = this.createSubImageAt(TOP_TO_BOTTOM, col);
            this.rightToLefts[col] = this.createSubImageAt(RIGHT_TO_LEFT, col);
            this.leftToRights[col] = this.createSubImageAt(LEFT_TO_RIGHT, col);
            this.bottomToTops[col] = this.createSubImageAt(BOTTOM_TO_TOP, col);
        }
    }

    public Bitmap[] getMoveBitmaps() {
        switch (rowUsing) {
            case BOTTOM_TO_TOP:
                return this.bottomToTops;
            case LEFT_TO_RIGHT:
                return this.leftToRights;
            case RIGHT_TO_LEFT:
                return this.rightToLefts;
            case TOP_TO_BOTTOM:
                return this.topToBottoms;
            default:
                return null;
        }
    }

    public Bitmap getCurrentMoveBitmap() {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }


    public void update() {

        if (movingVectorY == 0 &&
                movingVectorX == 0)
            return;

        //check if reached the target point or not
        if (hasReachedTargetPoint()) {
            controller.hasReachedDoor(x, y);
            movingVectorY = 0;
            movingVectorX = 0;
            return;
        }

        if (y + Utils.characterHeight < FLOOR_Y_END) {
            y = FLOOR_Y_END - Utils.characterHeight;
            movingVectorY = -movingVectorY;
//            return;
        }

        if (++ctr % refreshRate == 0)
            this.colUsing++;

        if (colUsing >= this.colCount) {
            this.colUsing = 0;
        }
        // Current time in nanoseconds
        long now = System.nanoTime();

        // Never once did draw.
        if (lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }
        // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

        // Distance moves
        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);


        // Calculate the new position of the game character.
        this.x = x + (int) (distance * movingVectorX / movingVectorLength);
        this.y = y + (int) (distance * movingVectorY / movingVectorLength);

        // When the game's character touches the edge of the screen, then change direction
        if (this.x < 0) {
            this.x = 0;
            this.movingVectorX = -this.movingVectorX;
            return;
        } else if (this.x > Utils.screenWidth - Utils.characterWidth) {
            this.x = Utils.screenWidth - Utils.characterWidth;
            this.movingVectorX = -this.movingVectorX;
        }

        if (this.y < 0) {
            this.y = 0;
            this.movingVectorY = -this.movingVectorY;
        } else if (this.y > Utils.screenHeight - Utils.characterHeight) {
            this.y = Utils.screenHeight - Utils.characterHeight;
            this.movingVectorY = -this.movingVectorY;
        }

        // rowUsing
        switch (getMovingDirection()) {
            case TOP_TO_BOTTOM:
                this.rowUsing = TOP_TO_BOTTOM;
                break;
            case BOTTOM_TO_TOP:
                this.rowUsing = BOTTOM_TO_TOP;
                break;
            case LEFT_TO_RIGHT:
                this.rowUsing = LEFT_TO_RIGHT;
                break;
            case RIGHT_TO_LEFT:
                this.rowUsing = RIGHT_TO_LEFT;
                break;
        }
    }

    /*
     * returns true if the object reached the target point
     */
    public boolean hasReachedTargetPoint() {
        int characterX = x + Utils.characterWidth;
        int characterY = y + Utils.characterHeight;

        switch (getMovingDirection()) {
            case TOP_TO_BOTTOM:
                if (characterY > targetY)
                    return true;
                break;
            case BOTTOM_TO_TOP:
                if (characterY < targetY)
                    return true;
                break;
            case LEFT_TO_RIGHT:
                if (characterX > targetX)
                    return true;
                break;
            case RIGHT_TO_LEFT:
                if (characterX < targetX)//solved little problem by subtracting 100 pixel
                    return true;
                break;
        }
        return false;
    }

    int getMovingDirection() {
        if (movingVectorX > 0) {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY))
                return TOP_TO_BOTTOM;
            else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY))
                return BOTTOM_TO_TOP;
            else
                return LEFT_TO_RIGHT;

        } else {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY))
                return TOP_TO_BOTTOM;
            else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY))
                return BOTTOM_TO_TOP;
            else
                return RIGHT_TO_LEFT;
        }
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x, y, null);
        // Last draw time.
        this.lastDrawNanoTime = System.nanoTime();
    }

    /*
     * coordinate on the surfaceview
     */
    private int targetX;
    private int targetY;

    public void setMovingVector(int targetX, int targetY) {
        //now the character foot will follow my touch instead of its head
        this.movingVectorX = targetX - Utils.characterWidth - getX();
        this.movingVectorY = targetY - Utils.characterHeight - getY();

        this.targetX = targetX;
        this.targetY = targetY;


    }

    void moveToTheRightOfScreen() {
        x = Utils.INITIAL_RIGHT_POSITION;
        movingVectorY = 0;
        movingVectorX = 0;
        rowUsing = RIGHT_TO_LEFT;
    }

    void moveToTheLeftOfScreen() {
        x = INITIAL_LEFT_POSITION;
        movingVectorY = 0;
        movingVectorX = 0;
        rowUsing = LEFT_TO_RIGHT;
    }
}
