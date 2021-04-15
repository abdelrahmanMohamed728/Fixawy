package com.example.fixawy.home.requests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.Request
import com.example.fixawy.model.SubDepartment
import com.example.fixawy.network.mapper.RequestMapper
import com.example.fixawy.network.model.RequestDTO
import com.example.fixawy.network.model.SubDepartmentDTO
import com.example.fixawy.network.repos.HomeRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RequestsViewModel(var mapper : RequestMapper , var repo : HomeRepo) : BaseViewModel() {
    var pendingFixerRequestsLiveData = MutableLiveData<List<Request>>()
    var pendingClientRequestsLiveData = MutableLiveData<List<Request>>()
    var acceptedFixerRequestsLiveData = MutableLiveData<List<Request>>()
    var acceptedClientRequestsLiveData = MutableLiveData<List<Request>>()

    fun getFixerPendingRequests(fixerId : Int) {
        var observable = repo.getFixerRequests(fixerId,1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { pendingFixerRequestsLiveData.postValue(mapRequests(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getFixerAccpetedRequests(fixerId : Int) {
        var observable = repo.getFixerRequests(fixerId,2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { acceptedFixerRequestsLiveData.postValue(mapRequests(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getClientPendingRequests(clientId : Int) {
        var observable = repo.getClientRequests(clientId,1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { pendingClientRequestsLiveData.postValue(mapRequests(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getClientAcceptedRequests(clientId : Int) {
        var observable = repo.getClientRequests(clientId,2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { acceptedClientRequestsLiveData.postValue(mapRequests(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    private fun mapRequests(it: List<RequestDTO>): List<Request> {
        var requests = mutableListOf<Request>()
        it.forEach { requestDTO ->
            requests.add(mapper.fromDomainModelToEntity(requestDTO))
        }
        return requests
    }

}