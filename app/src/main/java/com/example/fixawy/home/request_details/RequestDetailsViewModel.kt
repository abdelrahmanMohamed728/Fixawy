package com.example.fixawy.home.request_details

import android.os.Bundle
import com.example.base.BaseViewModel
import com.example.fixawy.model.Request

class RequestDetailsViewModel : BaseViewModel() {

    var request : Request? = null
    var mode : Int = -1
    fun extractArgs(arguments: Bundle?) {
        mode = arguments?.get("mode") as Int
        request = arguments.get("request") as Request
    }

}