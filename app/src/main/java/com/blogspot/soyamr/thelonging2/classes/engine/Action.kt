package com.blogspot.soyamr.thelonging2.classes.engine

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character

abstract class Action (val character: Character, val environment: Environment) {
    var finished : Boolean = false
    var curMicroAction : MicroAction? = null
    var status : Int = 0
    fun isFinished() : Boolean {
        return finished
    }
    abstract fun onPause() : Array<MicroAction>
    abstract fun onResume()
    abstract fun getCurrentMicroAction() : MicroAction?
}