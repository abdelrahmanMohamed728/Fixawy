package com.example.fixawy.network.repos

import com.example.fixawy.base.BaseRepo
import com.example.fixawy.network.mapper.CityMapper
import com.example.fixawy.network.model.CityDTO
import com.example.fixawy.network.model.JobDTO
import io.reactivex.Observable

class CityRepo : BaseRepo() {

    fun getCities():Observable<List<CityDTO>>{
        return apiManager.getCities()
    }

    fun getJobs() : Observable<List<JobDTO>>{
        return apiManager.getJobs()
    }
}