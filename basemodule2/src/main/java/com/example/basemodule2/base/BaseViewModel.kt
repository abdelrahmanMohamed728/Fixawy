package com.example.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected var mCompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}