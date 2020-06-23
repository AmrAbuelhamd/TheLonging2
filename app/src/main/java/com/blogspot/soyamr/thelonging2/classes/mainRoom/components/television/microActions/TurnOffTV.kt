package com.blogspot.soyamr.thelonging2.classes.mainRoom.components.television.microActions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.character.Character


class TurnOffTV (character: Character, environment: Environment) : MicroAction(character, environment){
    override fun isInterrupted(): Boolean {
        return false
    }

    override fun getDescription(): String {
        return "TurnOnTV: "
    }

    override fun endOfMicroAction() {
        environment.mainRoom.television.turnsOn = false
    }

}