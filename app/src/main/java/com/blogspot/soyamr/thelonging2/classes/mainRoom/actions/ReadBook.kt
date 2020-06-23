package com.blogspot.soyamr.thelonging2.classes.mainRoom.actions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.Action
import com.blogspot.soyamr.thelonging2.classes.engine.BrainAlertInterface
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.mainRoom.components.bookshelf.microActions.PutBook
import com.blogspot.soyamr.thelonging2.classes.mainRoom.components.bookshelf.microActions.ReadingBook
import com.blogspot.soyamr.thelonging2.classes.mainRoom.components.bookshelf.microActions.TakeBook

class ReadBook(character: Character, environment: Environment) : Action (character, environment), BrainAlertInterface {
    init {
        onAlert()
    }

    override fun onPause(): Array<MicroAction> {
        return arrayOf()
    }

    override fun onResume() {
    }

    override fun getCurrentMicroAction(): MicroAction? {
        return curMicroAction
    }

    override fun onAlert() {
        when(status){
            0 -> {
                curMicroAction = TakeBook(character, environment)
                character.brain.alert.addAlert(environment.current_time + 10 * 1000L, this)
            }
            1 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = ReadingBook(character, environment)
                character.brain.alert.addAlert(environment.current_time + 30 * 60 * 1000L, this)
            }
            2 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = PutBook(character, environment)
                character.brain.alert.addAlert(environment.current_time + 5 * 1000L, this)
            }
            3 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                finished = true
            }
        }
        status++

    }
}