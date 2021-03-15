package com.dixitpatel.biirr

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.airbnb.lottie.LottieAnimationView
import com.dixitpatel.biirr.ui.splash.SplashScreenActivity
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun verifySplashScreenActivityInjection() {
        ActivityScenario.launch(SplashScreenActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity ->

                Truth.assertThat((activity.findViewById<View>(R.id.animationView) as LottieAnimationView))
                    .isNotNull()
                Assert.assertTrue(activity.findViewById<View>(R.id.animationView).isVisible)

            }
        }
    }

}