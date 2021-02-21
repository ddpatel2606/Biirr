package com.dixitpatel.biirr.ui.splash

import android.view.View
import androidx.core.view.isVisible
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.dixitpatel.biirr.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest
{

    @Rule
    @JvmField
    var splashActivityRule: ActivityTestRule<SplashScreenActivity> = ActivityTestRule(
        SplashScreenActivity::class.java, false, true
    )

    @Test
    fun IsSplashViewVisible() {
        Assert.assertTrue(splashActivityRule.activity.findViewById<View>(R.id.animationView).isVisible)

    }
}