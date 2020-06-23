package com.blogspot.soyamr.thelonging2.classes

import com.blogspot.soyamr.thelonging2.classes.mainRoom.MainRoom

class Environment {
    var current_time : Long = 0 //текущее время в миллисекундах
    val mainRoom : MainRoom = MainRoom()

}