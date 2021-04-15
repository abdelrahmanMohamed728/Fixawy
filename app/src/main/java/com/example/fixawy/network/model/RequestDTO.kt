package com.example.fixawy.network.model

class RequestDTO {
    var id : Int? = 0
    var fixer : FixerDTO? = null
    var customer : ClientAuthDTO? = null
    var status : Int? = 0
    var orderDate : String? = ""
    var price : Int? = 0
    var subDepartmentId : Int? = 0
}