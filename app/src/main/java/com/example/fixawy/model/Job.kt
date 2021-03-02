package com.example.fixawy.model

class Job {
    var id : Int? = 0
    var name : String? = ""

    override fun equals(other: Any?): Boolean {
        var secondJob = other as Job
        return id == secondJob.id && name == secondJob.name
    }
}