package com.blogspot.soyamr.thelonging2

import android.content.Context

interface ViewParent {
    fun changeBackground(room: Room)
    fun getContext(): Context
}