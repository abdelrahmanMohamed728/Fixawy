package com.example.fixawy.network.model

import com.example.fixawy.model.City

class ClientAuthDTO {
    var id : Int? = null
    var nameEn : String? = null
    var nameAr : String? = null
    var mobile : String? = null
    var email : String? = null
    var password : String? = null
    var cityId : Int? = 0
}

class ClientLogInDTO {
    var id : Int? = null
    var nameEn : String? = null
    var nameAr : String? = null
    var mobile : String? = null
    var email : String? = null
    var password : String? = null
    var city: CityDTO? = null
}