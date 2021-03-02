package com.example.fixawy.model

class City {
    var id : Int? = 0
    var name : String? = null

    override fun equals(other: Any?): Boolean {
        var secondCity = other as City
        return secondCity.id == id && secondCity.name == name
    }
}