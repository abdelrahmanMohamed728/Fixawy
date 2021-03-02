package com.example.fixawy.authorization.client_signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.City
import com.example.fixawy.model.Client
import com.example.fixawy.network.mapper.CityMapper
import com.example.fixawy.network.mapper.ClientAuthMapper
import com.example.fixawy.network.model.CityDTO
import com.example.fixawy.network.repos.CityRepo
import com.example.fixawy.network.repos.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpViewModel(
    var repo: UserRepo,
    var clientAuthMapper: ClientAuthMapper,
    var cityRepo: CityRepo,
    var citiesMapper: CityMapper
) : BaseViewModel() {

    var signUpCompletedLiveData = MutableLiveData<Boolean>()
    var citiesLiveData = MutableLiveData<List<City>>()

    fun signUp(client: Client) {
        var observable = repo.clientSignUp(clientAuthMapper.fromEntityToDomainModel(client))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { signUpCompletedLiveData.postValue(true) }
            .doOnError {
                signUpCompletedLiveData.postValue(false)
            }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getCities() {
        var observable = cityRepo.getCities()
            .subscribeOn(Schedulers.io())
            .doOnNext { citiesLiveData.postValue(mapCities(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    private fun mapCities(citiesDTO: List<CityDTO>): List<City> {
        var cities = mutableListOf<City>()
        citiesDTO.forEach { city ->
            cities.add(citiesMapper.fromDomainModelToEntity(city))
        }
        return cities
    }

}