package com.blogspot.soyamr.thelonging2.classes.mainRoom.components.television

class Television {
    var turnsOn : Boolean = false
    var typeOfProgram : TypeOfPrograms = TypeOfPrograms.Cooking

    fun isTurnOn() : Boolean{
        return turnsOn
    }

    fun whichProgram() : TypeOfPrograms {
        return typeOfProgram
    }
}