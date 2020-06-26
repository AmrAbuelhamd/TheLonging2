package com.blogspot.soyamr.thelonging2.classes.character

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.actions.DoNothing
import com.blogspot.soyamr.thelonging2.classes.engine.Action

class Brain(val character: Character, private val environment: Environment) {
    val alert: BrainAlert = BrainAlert(character, environment)
    private var currentAction : Action = DoNothing(character, environment)

    fun addNewAction(action: Action){
        currentAction = action
    }

    fun cancelCurrentAction(){
        currentAction = DoNothing(character, environment)
    }

    fun getCurrentAction() : Action {
        return currentAction
    }

    private fun checkActiveAction() : Boolean{
        if(currentAction.getCurrentMicroAction() != null)
            return true
        return false
    }

}