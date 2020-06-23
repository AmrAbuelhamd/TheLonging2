package com.blogspot.soyamr.thelonging2.classes.mainRoom.components.bookshelf.microActions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction

class TakeBook(character: Character, environment: Environment) : MicroAction(character, environment) {
    override fun isInterrupted(): Boolean {
        return false
    }

    override fun getDescription(): String {
        return "TakeBook: take a book"
    }

    override fun endOfMicroAction() {}
}