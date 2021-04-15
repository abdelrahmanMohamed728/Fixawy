package com.example.fixawy.home.fixers


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.Fixer
import com.example.fixawy.network.common.GraphqlQueries
import com.example.fixawy.network.mapper.FixerAuthMapper
import com.example.fixawy.network.model.FixerDTO
import com.example.fixawy.network.repos.HomeRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FixersViewModel(var repo : HomeRepo, var mapper : FixerAuthMapper) : BaseViewModel() {

    var fixersLiveData = MutableLiveData<List<Fixer>>()

    fun getAllFixers(){
        var observable = repo.getAllFixers(GraphqlQueries.GET_ALL_FIXER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { fixersLiveData.postValue(mapFixers(it.data?.fixer?.getFixers)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    private fun mapFixers(fixersDTO: List<FixerDTO>?): List<Fixer> {
        var fixers = mutableListOf<Fixer>()
        fixersDTO?.forEach { fixer ->
            fixers.add(mapper.fromDomainModelToEntity(fixer))
        }
        return fixers
    }

}