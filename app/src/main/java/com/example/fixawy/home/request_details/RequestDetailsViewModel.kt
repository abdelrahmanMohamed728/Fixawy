package com.example.fixawy.home.request_details

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.Request
import com.example.fixawy.network.model.SubDepartmentDTO
import com.example.fixawy.network.repos.HomeRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RequestDetailsViewModel(var repo : HomeRepo) : BaseViewModel() {

    var rateUpdatedLiveData = MutableLiveData<Boolean>()
    var endFragmentLivedata = MutableLiveData<Boolean>()
    var subDepartmentLiveData = MutableLiveData<SubDepartmentDTO>()
    var request : Request? = null
    var mode : Int = -1
    fun extractArgs(arguments: Bundle?) {
        mode = arguments?.get("mode") as Int
        request = arguments.get("request") as Request
        if (request!= null && request!!.subDepartmentId != null)
        getCurrentSubDepartment(request!!.subDepartmentId!!)
    }

    fun getCurrentSubDepartment(id : Int) {
        var observable = repo.getAllSubDepartments()
            .subscribeOn(Schedulers.io())
            .doOnNext {subDepartmentLiveData.postValue(it.find { item -> item.id == id })}
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun rateFixer(fixerId : Int , rate : Float) {
        var observable = repo.rateFixer(fixerId,rate)
            .subscribeOn(Schedulers.io())
            .doOnComplete {rateUpdatedLiveData.postValue(true)}
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun cancelRequest(){
        var observable = repo.updateOrderStatus(request?.id!!,0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { endFragmentLivedata.postValue(true) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun acceptRequest(){
        var observable = repo.updateOrderStatus(request?.id!!,2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { endFragmentLivedata.postValue(true) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun finishRequest() {
        var observable = repo.updateOrderStatus(request?.id!!,3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { endFragmentLivedata.postValue(true) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

}