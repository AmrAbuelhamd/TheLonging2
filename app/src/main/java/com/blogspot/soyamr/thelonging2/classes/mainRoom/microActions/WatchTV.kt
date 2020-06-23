package com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.mainRoom.components.television.TypeOfPrograms

class WatchTV (character: Character, environment: Environment) : MicroAction(character, environment){
    override fun isInterrupted(): Boolean {
        return true
    }

    override fun getDescription(): String {
        return "WatchTV: "
    }

    override fun endOfMicroAction() {
        when (environment.mainRoom.television.typeOfProgram){
            TypeOfPrograms.Cartoon -> {
                character.params.reduceTiredness(2)
            }
            TypeOfPrograms.News -> {
                character.params.reduceAmbiguity(2)
            }
            TypeOfPrograms.Cooking -> {
                character.params.reduceTiredness(1)
                character.params.addHungry(1)
            }
        }

    }
}