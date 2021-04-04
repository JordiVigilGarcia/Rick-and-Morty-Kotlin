package com.android.data.di
import org.koin.dsl.module
import com.android.data.di.providers.*

val dataModule = module {
    single { provideTransactionRepository(get()) }
}