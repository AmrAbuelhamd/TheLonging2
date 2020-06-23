package com.blogspot.soyamr.thelonging2.classes.mainRoom.actions

import com.blogspot.soyamr.thelonging2.classes.Environment
import com.blogspot.soyamr.thelonging2.classes.engine.Action
import com.blogspot.soyamr.thelonging2.classes.engine.BrainAlertInterface
import com.blogspot.soyamr.thelonging2.classes.engine.MicroAction
import com.blogspot.soyamr.thelonging2.classes.character.Character
import com.blogspot.soyamr.thelonging2.classes.mainRoom.commonMicroActions.LieOnBed
import com.blogspot.soyamr.thelonging2.classes.mainRoom.components.television.microActions.*

class WatchTVAction (character: Character, environment: Environment) : Action (character, environment), BrainAlertInterface{
    init{
        onAlert()
    }

    override fun onPause(): Array<MicroAction> {
        return arrayOf()
    }
    override fun onResume() {}

    override fun getCurrentMicroAction(): MicroAction? {
        return curMicroAction
    }

    override fun onAlert() {
        when (status) {
            0 -> {
                curMicroAction =
                    LieOnBed(
                        character,
                        environment
                    )
                character.brain.alert.addAlert(environment.current_time + 5 * 1000L, this)
            }
            1 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = TurnOnTV(character, environment)
                if (!environment.mainRoom.television.turnsOn) {
                    character.brain.alert.addAlert(environment.current_time + 1 * 1000L, this)
                }
            }
            2 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = ChooseProgram(character, environment)
                character.brain.alert.addAlert(environment.current_time + 2 * 1000L, this)
                //TODO create a function for user for choosing program
            }
            3 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = WatchTV(character, environment)
                character.brain.alert.addAlert(environment.current_time + 30 * 60 * 1000L, this)
            }
            4 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                curMicroAction = TurnOffTV(character, environment)
                if (environment.mainRoom.television.turnsOn){
                    character.brain.alert.addAlert(environment.current_time + 1 * 1000L, this)
                }
            }
            5 -> {
                (curMicroAction as MicroAction).endOfMicroAction()
                finished = true
            }
        }
        status += 1

    }

}