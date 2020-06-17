package com.blogspot.soyamr.thelonging2.classes.work

import java.util.Random

class Vacancy (programmerStatus: Int) {
    private val random = Random()
    private val diff = if (programmerStatus + 2 < 9) programmerStatus + 2 else 9
    val difficulty: Int = (random.nextInt(diff) + 1)
    val duration: Int = random.nextInt(20 - 2) + 2
    val payment: Int = duration * difficulty * programmerStatus * 10
    val deadline: Int = (duration * 1.5).toInt()
    var chance: Double = 0.0

    init {
        chance = if (programmerStatus >= difficulty)
            (random.nextInt(5) + 5) / 10.0
        else
            random.nextInt(10) / 10.0
    }

}