package com.shreyas.go_train_schedule.di

import android.app.Application
import com.shreyas.go_train_schedule.MainApplication
import com.shreyas.go_train_schedule.di.modules.AppModule
import com.shreyas.go_train_schedule.di.modules.ServiceModule
import com.shreyas.go_train_schedule.di.modules.ViewModelFactoryModule
import com.shreyas.go_train_schedule.di.modules.ViewModelModule
import com.shreyas.go_train_schedule.di.modules.ViewModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ServiceModule::class,
        ViewModelFactoryModule::class,
        ViewModule::class,
        ViewModelModule::class,
        AppModule::class,
    ]
)
interface AppComponent {

    fun inject(mainApplication: MainApplication)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder
    }
}