package com.example.fixawy.network.repos

import com.example.fixawy.base.BaseRepo
import com.example.fixawy.model.UpdatePriceDTO
import com.example.fixawy.network.model.*
import io.reactivex.Completable
import io.reactivex.Observable

class HomeRepo : BaseRepo() {

    fun getAllSubDepartments() : Observable<List<SubDepartmentDTO>>{
        return apiManager.getSubDepartments()
    }

    fun getAllFixers(query : String) : Observable<AllFixersResponse>{
        return apiManager.dynamicQuery(query)
    }

    fun getFixerRequests(fixerId : Int , status : Int) : Observable<List<RequestDTO>>{
        return apiManager.getFixerRequests(fixerId , status)
    }

    fun getClientRequests(clientId : Int , status: Int) : Observable<List<RequestDTO>>{
        return apiManager.getCustomerRequests(clientId , status)
    }

    fun getAllDepartments() : Observable<List<SubDepartmentDTO>>{
        return apiManager.getDepartments()
    }

    fun updateOrderStatus(orderId : Int , status : Int) : Completable {
        return apiManager.updateOrderStatus(orderId,status)
    }

    fun addRequest(addRequestDTO: AddRequestDTO) : Completable {
        return apiManager.addOrder(addRequestDTO)
    }

    fun rateFixer(fixerId : Int , rate : Float) : Completable{
        return apiManager.rateFixer(fixerId,rate)
    }

    fun updatePrice(updatePriceDTO: UpdatePriceDTO) : Completable {
        return apiManager.updatePrice(updatePriceDTO)
    }
}