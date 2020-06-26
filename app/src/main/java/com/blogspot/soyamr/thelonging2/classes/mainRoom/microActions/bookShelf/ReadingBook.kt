package com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions.bookShelf

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction

class ReadingBook(character: Character, environment: Environment) : MicroAction(character, environment) {
    override fun isInterrupted(): Boolean {
        return true
    }

    override fun getDescription(): String {
        return "ReadingBook:"
    }

    override fun endOfMicroAction() {
        character.params.reduceLoneliness(2)
        character.params.reduceAmbiguity(1)
        character.params.addTiredness(1)
    }
}