package com.blogspot.soyamr.thelonging2.classes.mainRoom.components.television.microActions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.mainRoom.components.television.TypeOfPrograms

class ChooseProgram (character: Character, environment: Environment) : MicroAction(character, environment) {
    var typeOfProgram : TypeOfPrograms = TypeOfPrograms.Cartoon
    override fun isInterrupted(): Boolean {
        return false
    }

    override fun getDescription(): String {
        return "ChooseProgram: "
    }

    override fun endOfMicroAction() {
        environment.mainRoom.television.typeOfProgram = typeOfProgram
    }

    fun chooseProgram(type : TypeOfPrograms) {
        typeOfProgram = type
    }
}