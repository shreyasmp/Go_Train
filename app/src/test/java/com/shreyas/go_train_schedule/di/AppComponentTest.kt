package com.shreyas.go_train_schedule.di

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shreyas.go_train_schedule.MainApplication
import com.shreyas.go_train_schedule.repository.MetrolinxRepository
import com.shreyas.go_train_schedule.viewmodel.MetrolinxViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.DispatchingAndroidInjector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, TestAppModule::class])
interface TestAppComponent : AppComponent {
    override fun inject(mainApplication: MainApplication)
    fun activityInjector(): DispatchingAndroidInjector<Any>
}

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun provideMetrolinxRepository(): MetrolinxRepository {
        return mock(MetrolinxRepository::class.java)
    }

    @Provides
    @Singleton
    fun provideMetrolinxViewModel(repository: MetrolinxRepository): MetrolinxViewModel {
        return MetrolinxViewModel(repository)
    }
}

class AppComponentTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInjection() {
        val component: TestAppComponent = DaggerTestAppComponent.create()
        val application = MainApplication()
        component.inject(application)
        val repository = TestAppModule().provideMetrolinxRepository()
        val viewModel = TestAppModule().provideMetrolinxViewModel(repository)
        assertNotNull(viewModel)
    }
}