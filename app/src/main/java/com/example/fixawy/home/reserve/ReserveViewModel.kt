package com.example.fixawy.home.reserve

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.Job
import com.example.fixawy.network.mapper.JobMapper
import com.example.fixawy.network.model.AddRequestDTO
import com.example.fixawy.network.model.JobDTO
import com.example.fixawy.network.repos.CityRepo
import com.example.fixawy.network.repos.HomeRepo
import io.reactivex.schedulers.Schedulers

class ReserveViewModel(
    var cityRepo: CityRepo,
    var jobMapper: JobMapper,
    var repo: HomeRepo
) : BaseViewModel() {


    var jobsLiveData = MutableLiveData<List<Job>>()
    var completedLiveData = MutableLiveData<Boolean>()



    fun addRequest(addRequestDTO: AddRequestDTO){
        var observable = repo.addRequest(addRequestDTO)
            .subscribeOn(Schedulers.io())
            .doOnComplete {completedLiveData.postValue(true)}
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    fun getJobs(id : Int) {
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

    private fun mapJobs(it: List<JobDTO>): List<Job> {
        var jobs = mutableListOf<Job>()
        it.forEach { job ->
            jobs.add(jobMapper.fromDomainModelToEntity(job))
        }
        return jobs
    }

}