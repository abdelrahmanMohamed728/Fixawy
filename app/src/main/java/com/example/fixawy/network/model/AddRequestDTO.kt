package com.example.fixawy.network.model

class AddRequestDTO  {
    private var id : Int = 0
    private var type : Int = 0
    var orderDate : String ? = ""
    private var status : Int = 1
    var customerId : Int ? = 0
    var fixerId : Int ? = 0
    var subDepartmentId : Int? = 0
    var price : Int ? = 0
}