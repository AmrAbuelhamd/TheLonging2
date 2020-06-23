package com.blogspot.soyamr.thelonging2.classes.character

import com.blogspot.soyamr.thelonging2.classes.Environment

class Character (val environment: Environment) {
    var params : CharacterParams = CharacterParams()
    var programmer : CharacterProgrammer = CharacterProgrammer()
    val brain : Brain = Brain(this, environment)
//    fun onCreate(){
//        brain.onCreate()
//    }
}