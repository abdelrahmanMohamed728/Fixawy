package com.example.fixawy.model

open class User(
    var id: Int ? = null,
    var name: String
){
    companion object{
        const val CLIENT_TYPE = 0
        const val FIXER_TYPE = 1
    }
}