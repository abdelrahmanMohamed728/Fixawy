package com.example.fixawy.network.common

import com.example.fixawy.model.UpdatePriceDTO
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

    @GET("/api/Department/GetDepartments")
    fun getDepartments(): Observable<List<SubDepartmentDTO>>

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
    fun getJobs(): Observable<List<JobDTO>>

    @GET("/api/SubDepartment/GetSubDepartmentsByDepartmentID")
    fun getSubJobs(@Query("DepartmentID") departmentId : Int) : Observable<List<JobDTO>>

    @POST("/api/Fixer/UpdateFixer")
    fun updateFixer(@Body fixerDTO: FixerDTO): Completable

    @POST("/api/Customer/UpdateCustomer")
    fun updateClient(@Body clientAuthDTO: ClientAuthDTO): Completable

    @POST("api/Common/DynamicQuery")
    fun dynamicQuery(@Query("Query") query: String): Observable<AllFixersResponse>

    @GET("/api/Order/GetOrdersByFixerId")
    fun getFixerRequests(
        @Query("FixerId") fixerId: Int,
        @Query("Status") status: Int
    ): Observable<List<RequestDTO>>

    @GET("/api/Order/GetOrdersByCustomerId")
    fun getCustomerRequests(
        @Query("CustomerId") fixerId: Int,
        @Query("Status") status: Int
    ) : Observable<List<RequestDTO>>

    @POST("/api/Order/UpdateOrderStatus")
    fun updateOrderStatus(
        @Query("OrderId") orderId: Int,
        @Query("Status") status: Int
    ):Completable

    @POST("/api/Order/AddOrder")
    fun addOrder(@Body addRequestDTO : AddRequestDTO) : Completable

    @POST("/api/Fixer/UpdateRateByFixerId")
    fun rateFixer(
        @Query("FixerId") fixerId : Int ,
        @Query("Rate") rate : Float
    ) : Completable

    @POST("/api/Fixer/UpdateFixerMinPrice")
    fun updatePrice(@Body updatePriceDTO: UpdatePriceDTO) : Completable

}
