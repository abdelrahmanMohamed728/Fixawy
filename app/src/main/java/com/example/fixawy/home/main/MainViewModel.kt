package com.example.fixawy.home.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.fixawy.model.SubDepartment
import com.example.fixawy.network.mapper.SubDepartmentMapper
import com.example.fixawy.network.model.SubDepartmentDTO
import com.example.fixawy.network.repos.HomeRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(var repo: HomeRepo, var subDepartmentMapper: SubDepartmentMapper) :
    BaseViewModel() {

    var subDepartmentsLiveData = MutableLiveData<List<SubDepartment>>()

    fun getAllSubDepartments() {
        var observable = repo.getAllDepartments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { subDepartmentsLiveData.postValue(mapSubDepartments(it)) }
            .doOnError { handleNetworkError(it) }
            .subscribe({ Log.d("myTag", "observable.subscribe") },
                { throwable ->
                    Log.d("myTag", "observable.throwable: $throwable")
                })

        mCompositeDisposable.add(observable)
    }

    private fun mapSubDepartments(it: List<SubDepartmentDTO>): List<SubDepartment> {
        var subDepartments = mutableListOf<SubDepartment>()
        it.forEach { subDepartmentDTO ->
            subDepartments.add(subDepartmentMapper.fromDomainModelToEntity(subDepartmentDTO))
        }
        return subDepartments
    }

}