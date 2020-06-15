package com.blogspot.soyamr.thelonging2;

import android.graphics.Bitmap;

public class GameObject {
    protected Bitmap image;

    protected final int rowCount;
    protected final int colCount;

    protected final int imageWidth;
    protected final int imageHeight;

    protected final int characterWidth;
    protected final int characterHeight;

    protected int x;
    protected int y;

    //controls how often to change the character movements photo per frame
    protected int refreshRate = 10;
    //counter to track how many frame has passed
    protected int ctr =0;

    public GameObject(Bitmap image, int rowCount, int colCount, int x, int y) {
        super();
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;

        this.x = x;
        this.y = y;

        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.characterWidth = this.imageWidth / colCount;
        this.characterHeight = this.imageHeight / rowCount;
    }

    /**
     * Returns the subset of the photo that contains the character
     *
     * @param row defines which type of movements is required {out of four types of movements}
     * @param col defines which stage of movements is required
     * @return A bitmap that represents the specified character movement
     */
    protected Bitmap createSubImageAt(int row, int col) {
        // createBitmap(bitmap, x, y, width, height).
        Bitmap b = Bitmap.createBitmap(image, col * characterWidth, row * characterHeight,
                characterWidth, characterHeight);
        return Bitmap.createScaledBitmap(b, 97 * 2, 210 * 2, false);
    }
    /*
    * returns true if the object reached the target point
    * how it work: i am drawing a circle around the target point with radius equal to the object
    * height and i am checking if the object is inside the circle or not
     */
    protected boolean hasReachedTargetPoint(int targetX, int targetY, int movingVectorX, int movingVectorY) {
        int characterX = x + getCharacterWidth();
        int characterY = y + getCharacterHeight();

//         rowUsing
        if (movingVectorX > 0) {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                //TOP_TO_BOTTOM;
                if (characterY > targetY)
                    return true;
            } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                // BOTTOM_TO_TOP;
                if (characterY < targetY)
                    return true;
            } else {
                //LEFT_TO_RIGHT;
                if (characterX > targetX)
                    return true;
            }
        } else {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                //TOP_TO_BOTTOM;
                if (characterY > targetY)
                    return true;
            } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                //BOTTOM_TO_TOP;
                if (characterY < targetY)
                    return true;
            } else {
//                //RIGHT_TO_LEFT;
                if (characterX < targetX)
                    return true;
            }
        }

//        int circleRadius = 20;
//        return (characterX - targetX) * (characterX - targetX)
//                + (characterY - targetY) * (characterY - targetY) <
//                circleRadius*circleRadius;
        return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public int getCharacterHeight() {
        return 210 * 2;
    }

    public int getCharacterWidth() {
        return 97 * 2;
    }

    public void moveToRight() {
        x = 1800;
        y = 780;
    }
}
