package com.example.fixawy.home.client_profile.edit_profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.City
import com.example.fixawy.model.Client
import com.example.fixawy.model.Fixer
import com.example.fixawy.network.mapper.CityMapper
import com.example.fixawy.network.mapper.ClientAuthMapper
import com.example.fixawy.network.model.CityDTO
import com.example.fixawy.network.repos.CityRepo
import com.example.fixawy.network.repos.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClientEditProfileViewModel(
    var cityRepo: CityRepo,
    var mapper: CityMapper,
    var repo: UserRepo,
    var clientAuthMapper: ClientAuthMapper
) : BaseViewModel() {

    var citiesLiveData = MutableLiveData<List<City>>()
    var clientLiveData = MutableLiveData<Client>()

    var clientUpdatedLiveData = MutableLiveData<Boolean>()

    fun updateClient(client: Client) {
        var observable = repo.updateClient(clientAuthMapper.fromEntityToDomainModel(client))
            .subscribeOn(Schedulers.io())
            .doOnComplete {
                clientLiveData.postValue(client)
                clientUpdatedLiveData.postValue(true)
            }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getCities() {
        var observable = cityRepo.getCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { citiesLiveData.postValue(mapCities(it)) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    private fun mapCities(citiesDTO: List<CityDTO>): List<City> {
        var cities = mutableListOf<City>()
        citiesDTO.forEach { city ->
            cities.add(mapper.fromDomainModelToEntity(city))
        }
        return cities
    }
}