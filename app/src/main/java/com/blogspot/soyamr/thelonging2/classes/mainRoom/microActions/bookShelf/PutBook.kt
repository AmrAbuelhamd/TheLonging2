package com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions.bookShelf

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction

class PutBook(character: Character, environment: Environment) : MicroAction(character, environment) {
    override fun isInterrupted(): Boolean {
        return false
    }

    override fun getDescription(): String {
        return "PutBook: Put a book on a shelf"
    }

    override fun endOfMicroAction() {}
}