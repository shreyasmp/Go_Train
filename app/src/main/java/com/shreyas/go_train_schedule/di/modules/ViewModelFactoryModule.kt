package com.shreyas.go_train_schedule.di.modules

import androidx.lifecycle.ViewModelProvider
import com.shreyas.go_train_schedule.repository.MetrolinxRepository
import com.shreyas.go_train_schedule.repository.MetrolinxRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    abstract fun provideMetrolinxRepository(repositoryImpl: MetrolinxRepositoryImpl): MetrolinxRepository
}