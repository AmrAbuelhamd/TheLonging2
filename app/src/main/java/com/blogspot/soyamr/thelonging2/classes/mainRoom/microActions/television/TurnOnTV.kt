package com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions.television

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.character.Character

class TurnOnTV (character: Character, environment: Environment) : MicroAction (character, environment){
    override fun isInterrupted(): Boolean {
        return false
    }

    override fun getDescription(): String {
        return "TurnOnTV: "
    }

    override fun endOfMicroAction() {
        environment.mainRoom.television.turnsOn = true
    }

}