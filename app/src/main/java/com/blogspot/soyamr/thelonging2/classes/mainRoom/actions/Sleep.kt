package com.blogspot.soyamr.thelonging2.classes.mainRoom.actions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.engine.Action
import com.blogspot.soyamr.thelonging2.classes.engine.BrainAlertInterface
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions.GetUp
import com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions.LieOnBed
import com.blogspot.soyamr.thelonging2.classes.mainRoom.microActions.bed.Sleeping

class Sleep (character: Character, environment: Environment) : Action(character, environment),
    BrainAlertInterface {
    override fun onPause(): Array<MicroAction> {
        return arrayOf()
    }

    override fun onResume() {}

    override fun getCurrentMicroAction(): MicroAction? {
        return curMicroAction
    }

    override fun onAlert() {
        when(status){
            0 -> {
                curMicroAction = LieOnBed(character, environment)
                character.brain.alert.addAlert(environment.current_time + 5 * 1000L, this)
            }
            1 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = Sleeping(character, environment)
                character.brain.alert.addAlert(environment.current_time + 8 * 60 * 60 * 1000L, this)
            }
            2 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = GetUp(character, environment)
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