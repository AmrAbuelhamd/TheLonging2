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
        return Bitmap.createBitmap(image, col * characterWidth, row * characterHeight,
                characterWidth, characterHeight);
    }
    /*
    * returns true if the object reached the target point
    * how it work: i am drawing a circle around the target point with radius equal to the object
    * height and i am checking if the object is inside the circle or not
     */
    protected boolean hasReachedTargetPoint(int targetX, int targetY) {
        int circleRadius = characterHeight;
        return (x - targetX) * (x - targetX) + (y - targetY) * (y - targetY) <
                circleRadius*circleRadius;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public int getCharacterHeight() {
        return characterHeight;
    }

    public int getCharacterWidth() {
        return characterWidth;
    }
}
