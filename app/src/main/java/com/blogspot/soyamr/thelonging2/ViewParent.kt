package com.blogspot.soyamr.thelonging2

import android.content.Context
import android.graphics.Bitmap
import com.blogspot.soyamr.thelonging2.elements.house.Room

interface ViewParent {
    fun changeBackground(room: Room)
    fun getContext(): Context
    fun getRoomBitmap(roomID: Int): Bitmap?
    fun addButtonBookShelf()
    fun removeButtonOpenShelf()
    fun addButtonGoBack()
    fun removeButtonGoBack()
    fun addButtonOpenFlyGame()
    fun removeButtonOpenFlyGame()

}