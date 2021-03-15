package com.dixitpatel.biirr.ui.splash

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dixitpatel.biirr.R
import com.dixitpatel.biirr.databinding.ActivityMainBinding
import com.dixitpatel.biirr.databinding.ActivitySplashBinding
import com.dixitpatel.biirr.ui.base.BaseActivity
import com.dixitpatel.biirr.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.BufferedReader
import javax.inject.Inject

/**
 *  Splash Activity Class : Display lottie animation.
 */
@AndroidEntryPoint
class SplashScreenActivity : BaseActivity() {

    val model : SplashScreenViewModel by viewModels()

    private val binding: ActivitySplashBinding by binding(R.layout.activity_splash)

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Default + viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.animationView.playAnimation()

        uiScope.launch {
            delay(5000)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }
    }

    // When on Destroy method call cancel coroutine to avoid Memory leak errors.
    override fun onDestroy() {
        super.onDestroy()
        if(viewModelJob.isActive) {
            viewModelJob.cancel()
        }
    }

}