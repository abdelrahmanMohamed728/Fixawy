package com.example.fixawy.home.past_requests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.Request
import com.example.fixawy.network.mapper.RequestMapper
import com.example.fixawy.network.model.RequestDTO
import com.example.fixawy.network.repos.HomeRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PastRequestsViewModel(var mapper : RequestMapper, var repo : HomeRepo) : BaseViewModel() {

    var pastFixerRequestsLiveData = MutableLiveData<List<Request>>()
    var pastClientRequestsLiveData = MutableLiveData<List<Request>>()

    fun getFixerPastRequests(fixerId : Int) {
        var observable = repo.getFixerRequests(fixerId,3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { pastFixerRequestsLiveData.postValue(mapRequests(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getClientPastRequests(clientId : Int) {
        var observable = repo.getClientRequests(clientId,3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { pastClientRequestsLiveData.postValue(mapRequests(it)) }
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