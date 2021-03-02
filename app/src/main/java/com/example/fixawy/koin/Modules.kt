package com.example.fixawy.koin

import com.example.base.BaseViewModel
import com.example.fixawy.authorization.login.LogInViewModel
import com.example.fixawy.authorization.client_signup.SignUpViewModel
import com.example.fixawy.authorization.fixer_signup.FixerSignUpViewModel
import com.example.fixawy.home.client_profile.edit_profile.ClientEditProfileViewModel
import com.example.fixawy.home.client_profile.show_profile.ClientProfileViewModel
import com.example.fixawy.home.fixer_profile.edit_profile.FixerEditProfileViewModel
import com.example.fixawy.home.fixer_profile.show_profile.FixerProfileViewModel
import com.example.fixawy.home.main.MainViewModel
import com.example.fixawy.home.past_requests.PastRequestsViewModel
import com.example.fixawy.home.request_details.RequestDetailsViewModel
import com.example.fixawy.home.requests.RequestsViewModel
import com.example.fixawy.home.search.SearchViewModel
import com.example.fixawy.network.common.WebService
import com.example.fixawy.network.mapper.*
import com.example.fixawy.network.repos.CityRepo
import com.example.fixawy.network.repos.HomeRepo
import com.example.fixawy.network.repos.UserRepo
import com.example.fixawy.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelsModule = module {
    viewModel {
        ClientProfileViewModel()
    }
    viewModel {
        FixerEditProfileViewModel(get(),get(),get(),get(),get())
    }
    viewModel {
        RequestDetailsViewModel()
    }
    viewModel {
        RequestsViewModel()
    }
    viewModel {
        PastRequestsViewModel()
    }
    viewModel {
        ClientEditProfileViewModel(get(),get(),get(),get())
    }
    viewModel {
        SignUpViewModel(get(),get(),get(),get())
    }
    viewModel {
        MainViewModel(get(),get())
    }
    viewModel {
        LogInViewModel(get(),get(),get())
    }
    viewModel {
        FixerSignUpViewModel(get(),get(),get(),get(),get())
    }
    viewModel {
        StartViewModel()
    }
    viewModel {
        BaseViewModel()
    }
    viewModel {
        SearchViewModel()
    }
    viewModel {
        FixerProfileViewModel()
    }
}

val repoModule = module {
    single {
        UserRepo()
    }
    factory(named("BASE_URL")) {
        "https://fixstation.azurewebsites.net"
    }

    factory {
        CityRepo()
    }

    factory {
        HomeRepo()
    }

    factory {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WebService::class.java)
    }
}

val mapperModule = module {

    factory {
        SubDepartmentMapper()
    }

    factory {
        ClientAuthMapper()
    }

    factory{
        CityMapper()
    }

    factory {
        ClientLogInMapper()
    }
    factory {
        JobMapper()
    }
    factory {
        FixerAuthMapper()
    }
    factory {
        FixerLogInMapper()
    }
}

