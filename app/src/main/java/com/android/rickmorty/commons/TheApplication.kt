package com.android.rickmorty.commons

import android.app.Application
import com.android.data.di.dataModule
import com.android.data.models.RickMorty
import com.android.rickmorty.detail_screen.vm.ViewModelShared
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TheApplication : Application() {

    private val module = module {
        viewModel { ViewModelProfile( get() ) }
        viewModel { ViewModelShared() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TheApplication)
            modules(listOf(dataModule, module))
        }
    }
}