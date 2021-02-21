package com.dixitpatel.biirr.ui.main

import androidx.lifecycle.ViewModelProvider
import com.dixitpatel.biirr.repository.MainViewRepository
import com.dixitpatel.biirr.repository.Repository
import com.dixitpatel.biirr.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

/**
 *  Main Activity Module : MainActivity bind with ViewModel
 */
@Module
class MainActivityModule {

    @Provides
    fun providesMainViewRepository() : Repository {
        return MainViewRepository()
    }

    @Provides
    fun providesViewModel(mainViewRepository: MainViewRepository): MainActivityViewModel {
        return MainActivityViewModel(mainViewRepository)
    }

    @Provides
    fun provideViewModelProvider(viewModel: MainActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}