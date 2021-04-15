package com.example.fixawy.home.change_prices

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.Job
import com.example.fixawy.model.UpdatePriceDTO
import com.example.fixawy.network.mapper.JobMapper
import com.example.fixawy.network.model.JobDTO
import com.example.fixawy.network.repos.CityRepo
import com.example.fixawy.network.repos.HomeRepo
import io.reactivex.schedulers.Schedulers

class ChangePricesViewModel(var jobMapper: JobMapper, var cityRepo: CityRepo, var repo: HomeRepo) :
    BaseViewModel() {

    var priceUpdatedLiveData = MutableLiveData<Boolean>()
    var jobsLiveData = MutableLiveData<List<Job>>()
    fun getJobs(id: Int) {
        var observable = cityRepo.getSubJobs(id)
            .subscribeOn(Schedulers.io())
            .doOnNext { jobsLiveData.postValue(mapJobs(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun updatePrice(fixerId: Int, subDepartmentId: Int, price: Int) {
        var updatePriceDTO = UpdatePriceDTO()
        updatePriceDTO.fixerId = fixerId
        updatePriceDTO.minPrice = price
        updatePriceDTO.subDepartmentId = subDepartmentId
        var observable = repo.updatePrice(updatePriceDTO)
            .subscribeOn(Schedulers.io())
            .doOnComplete { priceUpdatedLiveData.postValue(true) }
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

}