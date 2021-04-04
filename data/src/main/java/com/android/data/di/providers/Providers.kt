package com.android.data.di.providers

import android.app.Application


import com.android.data.repository.RickMortyRepository

fun provideTransactionRepository(application: Application): RickMortyRepository = RickMortyRepository(application)