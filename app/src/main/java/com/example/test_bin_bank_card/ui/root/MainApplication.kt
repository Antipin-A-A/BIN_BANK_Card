package com.example.test_bin_bank_card.ui.root

import android.app.Application
import com.example.test_bin_bank_card.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(AppModule)
        }
    }
}
