package com.dixitpatel.biirr.di.activitybuilder

import com.dixitpatel.biirr.ui.detail.DetailViewActivity
import com.dixitpatel.biirr.ui.detail.DetailViewActivityModule
import com.dixitpatel.biirr.ui.main.MainActivity
import com.dixitpatel.biirr.ui.main.MainActivityModule
import com.dixitpatel.biirr.ui.splash.SplashScreenActivity
import com.dixitpatel.biirr.ui.splash.SplashScreenActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * This is Dagger Activity Builder Which activities will be used in app must be add
 * in Dagger Module.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity?

    @ContributesAndroidInjector(modules = [DetailViewActivityModule::class])
    abstract fun contributeDetailActivity(): DetailViewActivity?

    @ContributesAndroidInjector(modules = [SplashScreenActivityModule::class])
    abstract fun contributeSplashActivity(): SplashScreenActivity?

}