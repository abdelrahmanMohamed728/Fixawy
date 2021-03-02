package com.example.fixawy.base

import com.example.fixawy.network.common.WebService
import org.koin.core.context.GlobalContext

open class BaseRepo {
    var apiManager = GlobalContext.get().koin.get<WebService>()
}