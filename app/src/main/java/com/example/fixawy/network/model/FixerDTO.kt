package com.example.fixawy.network.model

import com.example.fixawy.model.price

class FixerDTO  {
    var id : Int? = 0
    var email : String? = ""
    var name : String? = ""
    var mobile : String? = ""
    var password : String? = ""
    var departmentId : Int? = 0
    var cityId : Int? = 0
    var rate : Float? = 0f
    var prices : List<price>? = null
    var identityNo : String? = null
}