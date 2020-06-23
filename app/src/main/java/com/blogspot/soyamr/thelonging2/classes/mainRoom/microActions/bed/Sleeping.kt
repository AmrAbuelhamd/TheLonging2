package com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions.bed

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.character.Character

class Sleeping(character: Character, environment: Environment) : MicroAction (character, environment) {
    override fun isInterrupted(): Boolean {
        return true
    }

    override fun getDescription(): String {
        return "Sleeping:"
    }

    override fun endOfMicroAction() {
        character.params.reduceTiredness(10)
        character.params.addHungry(4)
        character.params.addAmbiguity(1)
    }
}