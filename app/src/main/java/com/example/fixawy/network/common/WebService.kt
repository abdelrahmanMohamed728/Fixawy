package com.example.fixawy.network.common

import com.example.fixawy.network.model.*
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebService {

    @GET("/api/City/GetCities")
    fun getCities(): Observable<List<CityDTO>>

    @POST("/api/Customer/Register")
    fun clientSignUp(@Body authDTO: ClientAuthDTO): Completable

    @POST("/api/Fixer/Register")
    fun fixerSignUp(@Body authDTO: FixerDTO): Completable

    @GET("/api/SubDepartment/GetSubDepartments")
    fun getSubDepartments(): Observable<List<SubDepartmentDTO>>

    @POST("/api/Customer/Login")
    fun clientLogIn(
        @Query("Email") email: String,
        @Query("Password") password: String
    ): Observable<ClientLogInResponse>

    @POST("/api/Fixer/Login")
    fun fixerLogIn(
        @Query("Email") email: String,
        @Query("Password") password: String
    ): Observable<FixerLogInResponse>

    @GET("/api/Department/GetDepartments")
    fun getJobs() : Observable<List<JobDTO>>

    @POST("/api/Fixer/UpdateFixer")
    fun updateFixer(@Body fixerDTO: FixerDTO) : Completable

    @POST("/api/Customer/UpdateCustomer")
    fun updateClient(@Body clientAuthDTO: ClientAuthDTO) : Completable

}
