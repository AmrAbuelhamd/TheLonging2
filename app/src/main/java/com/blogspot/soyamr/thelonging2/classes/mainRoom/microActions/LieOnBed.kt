package com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction

class LieOnBed (character: Character, environment: Environment) : MicroAction(character, environment) {
    override fun isInterrupted(): Boolean {
        return false
    }

    override fun getDescription(): String {
        return "LieInBed: lie down on the bed"
    }

    override fun endOfMicroAction() {}
}