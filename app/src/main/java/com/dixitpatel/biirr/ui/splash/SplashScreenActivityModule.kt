package com.dixitpatel.biirr.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.dixitpatel.biirr.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SplashScreenActivityModule {

    @Provides
    fun providesViewModel(): SplashScreenViewModel {
        return SplashScreenViewModel()
    }

    @Provides
    fun provideViewModelProvider(viewModel: SplashScreenViewModel?): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}