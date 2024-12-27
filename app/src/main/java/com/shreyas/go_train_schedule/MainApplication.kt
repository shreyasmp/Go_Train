package com.shreyas.go_train_schedule

import android.app.Application
import com.shreyas.go_train_schedule.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = activityInjector

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }
}