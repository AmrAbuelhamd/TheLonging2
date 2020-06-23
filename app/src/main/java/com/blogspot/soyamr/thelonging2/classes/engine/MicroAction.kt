package com.blogspot.soyamr.thelonging2.classes.engine

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character

abstract class MicroAction (val character: Character, val environment: Environment){
    abstract fun isInterrupted() : Boolean
    abstract fun getDescription() : String
    abstract fun endOfMicroAction()
}