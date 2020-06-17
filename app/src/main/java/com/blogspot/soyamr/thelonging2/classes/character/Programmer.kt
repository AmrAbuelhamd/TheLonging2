package com.blogspot.soyamr.thelonging2.classes.character

interface CharacterProgrammerInterface{
    fun raiseProgrammerStatus(difficulty: Int, duration: Int)
    fun getStatus() : Int
}

open class CharacterProgrammer : CharacterProgrammerInterface {
    var programmerStatus : Double = 1.0 // статус на фрилансе
    var inProcess : Boolean = false

    override fun raiseProgrammerStatus(difficulty: Int, duration: Int){
        programmerStatus += difficulty * 0.1 + duration * 0.01
    }

    override fun getStatus() : Int {
        if (programmerStatus > 10) return 10
        return programmerStatus.toInt()
    }
}