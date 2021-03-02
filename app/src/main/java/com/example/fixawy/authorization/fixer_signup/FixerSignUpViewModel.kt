package com.example.fixawy.authorization.fixer_signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.BaseViewModel
import com.example.fixawy.model.*
import com.example.fixawy.network.mapper.CityMapper
import com.example.fixawy.network.mapper.FixerAuthMapper
import com.example.fixawy.network.mapper.JobMapper
import com.example.fixawy.network.model.CityDTO
import com.example.fixawy.network.model.JobDTO
import com.example.fixawy.network.repos.CityRepo
import com.example.fixawy.network.repos.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class FixerSignUpViewModel(
    var cityRepo: CityRepo,
    var citiesMapper: CityMapper,
    var jobMapper: JobMapper,
    var repo: UserRepo,
    var fixerAuthMapper: FixerAuthMapper
) : BaseViewModel() {

    var signUpCompletedLiveData = MutableLiveData<Boolean>()
    var citiesLiveData = MutableLiveData<List<City>>()
    var jobsLiveData = MutableLiveData<List<Job>>()

    fun signUp(fixer: Fixer) {
        var observable = repo.fixerSignUp(fixerAuthMapper.fromEntityToDomainModel(fixer))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { signUpCompletedLiveData.postValue(true) }
            .doOnError {
                signUpCompletedLiveData.postValue(false)
                handleNetworkError(it)
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
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getJobs() {
        var observable = cityRepo.getJobs()
            .subscribeOn(Schedulers.io())
            .doOnNext { jobsLiveData.postValue(mapJobs(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    private fun mapJobs(it: List<JobDTO>): List<Job> {
        var jobs = mutableListOf<Job>()
        it.forEach { job ->
            jobs.add(jobMapper.fromDomainModelToEntity(job))
        }
        return jobs
    }

    private fun mapCities(citiesDTO: List<CityDTO>): List<City> {
        var cities = mutableListOf<City>()
        citiesDTO.forEach { city ->
            cities.add(citiesMapper.fromDomainModelToEntity(city))
        }
        return cities
    }
}