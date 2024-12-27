package com.shreyas.go_train_schedule.di.modules

import com.shreyas.go_train_schedule.view.MainActivity
import com.shreyas.go_train_schedule.di.annotations.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}