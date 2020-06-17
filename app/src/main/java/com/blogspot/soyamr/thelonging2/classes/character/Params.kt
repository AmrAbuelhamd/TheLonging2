package com.blogspot.soyamr.thelonging2.classes.character

interface CharacterParamsInterface{
    fun addHungry(value: Int)
    fun reduceHungry(value: Int)
    fun addLoneliness(value: Int)
    fun reduceLoneliness(value: Int)
    fun addTiredness(value: Int)
    fun reduceTiredness(value: Int)
    fun addAmbiguity(value: Int)
    fun reduceAmbiguity(value: Int)
}

open class CharacterParams :
    CharacterParamsInterface {
    var hungry : Int = 0 // голод, восполняется едой
    var loneliness : Int = 0 // одиночество, восп общением
    var tiredness : Int = 0 // усталость - отдыхом
    var ambiguity : Int = 0 // неясность, неведение - просмотром и чтением новостей, общением

    override fun addHungry(value: Int){
        hungry += value
        checkParams("hungry")
    }
    override fun reduceHungry(value: Int){
        hungry -= value
        checkParams("hungry")
    }
    override fun addLoneliness(value: Int){
        loneliness += value
        checkParams("loneliness")
    }
    override fun reduceLoneliness(value: Int){
        loneliness -= value
        checkParams("loneliness")
    }
    override fun addTiredness(value: Int){
        tiredness += value
        checkParams("tiredness")
    }
    override fun reduceTiredness(value: Int){
        tiredness -= value
        checkParams("tiredness")
    }
    override fun addAmbiguity(value: Int){
        ambiguity += value
        checkParams("ambiguity")
    }
    override fun reduceAmbiguity(value: Int){
        ambiguity -= value
        checkParams("ambiguity")
    }


    private fun checkParams(type : String){
        when(type) {
            "hungry" ->
                if (hungry < 0) hungry = 0
                else if (hungry >= 100) {
                    hungry = 100
                }
            "loneliness" ->
                if (loneliness < 0) loneliness = 0
                else if (loneliness >= 100) {
                    loneliness = 100
                }
            "tiredness" ->
                if (tiredness < 0) tiredness = 0
                else if (tiredness >= 100) {
                    tiredness = 100
                }
            "ambiguity" ->
                if (ambiguity < 0) ambiguity = 0
                else if (ambiguity >= 100) {
                    ambiguity = 100
                }
        }
    }

}