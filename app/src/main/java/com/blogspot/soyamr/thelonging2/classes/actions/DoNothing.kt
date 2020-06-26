package com.blogspot.soyamr.thelonging2.classes.actions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.Action
import com.blogspot.soyamr.thelonging2.classes.engine.BrainAlertInterface
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.microActions.DoNothingMA

class DoNothing(character : Character, environment: Environment) : Action(character, environment), BrainAlertInterface {
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
                curMicroAction = DoNothingMA(character, environment)
            }
        }
    }
}