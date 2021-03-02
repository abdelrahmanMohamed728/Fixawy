package com.example.fixawy.network.repos

import com.example.fixawy.base.BaseRepo
import com.example.fixawy.network.model.SubDepartmentDTO
import io.reactivex.Observable

class HomeRepo : BaseRepo() {

    fun getAllSubDepartments() : Observable<List<SubDepartmentDTO>>{
        return apiManager.getSubDepartments()
    }
}