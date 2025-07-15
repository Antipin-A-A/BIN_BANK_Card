package com.example.test_bin_bank_card.di

import androidx.room.Room
import com.example.test_bin_bank_card.data.NetworkClient
import com.example.test_bin_bank_card.data.bd.AppDataBase
import com.example.test_bin_bank_card.data.network.ConnectedManager
import com.example.test_bin_bank_card.data.network.RepositoryImpl
import com.example.test_bin_bank_card.data.network.RetrofitManager
import com.example.test_bin_bank_card.data.network.RetrofitNetworkClient
import com.example.test_bin_bank_card.domain.api.Interact
import com.example.test_bin_bank_card.domain.api.Repository
import com.example.test_bin_bank_card.domain.imp.InteractImp
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.test_bin_bank_card.ui.viewmodel.FragmentViewModel

val AppModule = module {

    factory {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, "database.db")
            .build()
    }
    single { Gson() }

    single { RetrofitManager() }

    single { ConnectedManager(androidContext()) }

    single<NetworkClient> { RetrofitNetworkClient(get(), get()) }

//    factoryOf(::RepositoryImpl) {
//        bind<RepositoryImpl>()
//    }
    single<Repository> { RepositoryImpl(get(), get()) }

    single<Interact> { InteractImp(get()) }

//    single<OkHttpClient> {
//        val loggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .build()
//    }

    viewModel<FragmentViewModel> { FragmentViewModel(interactor = get()) }
}