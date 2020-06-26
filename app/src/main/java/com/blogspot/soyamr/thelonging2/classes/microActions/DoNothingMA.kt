package com.blogspot.soyamr.thelonging2.classes.microActions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction

class DoNothingMA(character : Character, environment: Environment) : MicroAction(character, environment) {
    override fun isInterrupted(): Boolean {
        return true
    }

    override fun getDescription(): String {
        return "DoNothingMA : "
    }

    override fun endOfMicroAction() {
    }
}