package com.dixitpatel.biirr.ui.detail

import androidx.lifecycle.ViewModelProvider
import com.dixitpatel.biirr.repository.Repository
import com.dixitpatel.biirr.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

/**
 *  Detail Activity Module : DetailActivity bind with ViewModel
 */
@Module
class DetailViewActivityModule {


    @Provides
    fun provideViewModelProvider(viewModel: DetailViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
    
}