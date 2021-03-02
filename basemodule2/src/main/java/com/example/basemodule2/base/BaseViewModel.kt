package com.example.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basemodule2.base.FixawyError
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import retrofit2.adapter.rxjava2.Result.response


open class BaseViewModel : ViewModel() {
    protected var mCompositeDisposable = CompositeDisposable()

    var errorsLiveData = MutableLiveData<FixawyError>()
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }

    fun handleNetworkError(throwable: Throwable){
        if (throwable is HttpException){
            val gson = Gson()
            val message: FixawyError = gson.fromJson(
                throwable.response().errorBody()?.charStream(),
                FixawyError::class.java
            )

            errorsLiveData.postValue(message)
        }
        else {
            var error = FixawyError()
            error.messageAr = "General Error"
            errorsLiveData.postValue(error)
        }
    }
}