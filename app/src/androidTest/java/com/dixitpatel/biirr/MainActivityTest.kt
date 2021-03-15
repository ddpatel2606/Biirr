package com.dixitpatel.biirr

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dixitpatel.biirr.ui.main.MainActivity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest
{
        @get:Rule
        var hiltRule = HiltAndroidRule(this)

        @Test
        fun verifyMainActivityInjection() {
            ActivityScenario.launch(MainActivity::class.java).use {
                it.moveToState(Lifecycle.State.CREATED)
                it.onActivity { activity ->
                    Assert.assertTrue(activity.findViewById<View>(R.id.myRecyclerView).isVisible)
                    assertThat(activity.model).isNotNull()
                    activity.model.mainViewRepository.beerApiResult().observe(activity) { list ->
                        assertThat(list).isNotNull()
                    }
                }
            }
        }

}