package com.example.fixawy.network.model

import com.example.fixawy.model.Fixer

class AllFixersResponse {
    var data: AllFixersResponse2? = null

    class AllFixersResponse2 {
        var fixer: AllFixersResponse3? = null
    }

    class AllFixersResponse3 {
        var getFixers: List<FixerDTO>? = null
    }
}


