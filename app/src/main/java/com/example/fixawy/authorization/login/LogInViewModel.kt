package com.example.fixawy.authorization.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.network.mapper.ClientLogInMapper
import com.example.fixawy.network.mapper.FixerLogInMapper
import com.example.fixawy.network.model.LogInModel
import com.example.fixawy.network.repos.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LogInViewModel(
    var clientAuthMapper: ClientLogInMapper,
    var repo: UserRepo,
    var fixerLogInMapper: FixerLogInMapper
) : BaseViewModel() {
    var completedClientLogInLiveData = MutableLiveData<Boolean>()
    fun logIn(email: String, password: String, isClient: Boolean) {
        if (isClient) {
            var logInModel = LogInModel()
            logInModel.email = email
            logInModel.password = password
            var observable = repo.clientLogIn(logInModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    completedClientLogInLiveData.postValue(true)
                    if (it.result != null)
                        repo.clientLiveData.postValue(clientAuthMapper.fromDomainModelToEntity(it.result!!))
                }
                .doOnError {
                    completedClientLogInLiveData.postValue(false)
                    handleNetworkError(it)
                }
                .subscribe({ Log.d("myTag", "observable.subscribe") },
                    { throwable ->
                        Log.d("myTag", "observable.throwable: $throwable")
                    })

            mCompositeDisposable.add(observable)
        } else {
            var logInModel = LogInModel()
            logInModel.email = email
            logInModel.password = password
            var observable = repo.fixerLogIn(logInModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    completedClientLogInLiveData.postValue(true)
                    if (it.result != null)
                        repo.fixerLiveData.postValue(fixerLogInMapper.fromDomainModelToEntity(it.result!!))
                }
                .doOnError {
                    completedClientLogInLiveData.postValue(false)
                    handleNetworkError(it)
                }
                .subscribe({ Log.d("myTag", "observable.subscribe") },
                    { throwable ->
                        Log.d("myTag", "observable.throwable: $throwable")
                    })

            mCompositeDisposable.add(observable)
        }
    }
}