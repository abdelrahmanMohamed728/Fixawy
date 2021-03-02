package com.example.fixawy.network.repos

import androidx.lifecycle.MutableLiveData
import com.example.fixawy.base.BaseRepo
import com.example.fixawy.model.Client
import com.example.fixawy.model.Fixer
import com.example.fixawy.network.model.*
import io.reactivex.Completable
import io.reactivex.Observable

class UserRepo : BaseRepo() {
    var type: Int = 0
    var fixerLiveData = MutableLiveData<Fixer>()
    var clientLiveData = MutableLiveData<Client>()

    fun clientSignUp(clientAuthDTO: ClientAuthDTO): Completable {
        return apiManager.clientSignUp(clientAuthDTO)
    }

    fun fixerSignUp(fixerDTO: FixerDTO): Completable {
        return apiManager.fixerSignUp(fixerDTO)
    }

    fun clientLogIn(logInModel: LogInModel): Observable<ClientLogInResponse> {
        return apiManager.clientLogIn(logInModel.email, logInModel.password)
    }

    fun fixerLogIn(logInModel: LogInModel): Observable<FixerLogInResponse> {
        return apiManager.fixerLogIn(logInModel.email, logInModel.password)
    }

    fun updateFixer(fixerDTO: FixerDTO): Completable {
        return apiManager.updateFixer(fixerDTO)
    }

    fun updateClient(clientAuthDTO: ClientAuthDTO) : Completable {
        return apiManager.updateClient(clientAuthDTO)
    }


    companion object {
        var userRepo = UserRepo()
        fun getInstance(): UserRepo {
            return userRepo
        }

        const val CLIENT_TYPE = 0
        const val FIXER_TYPE = 1
    }
}