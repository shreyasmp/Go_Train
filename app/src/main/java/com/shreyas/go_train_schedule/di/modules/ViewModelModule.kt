package com.shreyas.go_train_schedule.di.modules

import androidx.lifecycle.ViewModel
import com.shreyas.go_train_schedule.di.annotations.ViewModelKey
import com.shreyas.go_train_schedule.viewmodel.MetrolinxViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MetrolinxViewModel::class)
    internal abstract fun bindMetrolinxViewModel(metrolinxViewModel: MetrolinxViewModel): ViewModel
}
