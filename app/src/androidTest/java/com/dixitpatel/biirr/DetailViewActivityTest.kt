package com.dixitpatel.biirr

import android.content.Intent
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dixitpatel.biirr.ui.detail.DetailViewActivity
import com.dixitpatel.biirr.ui.detail.DetailViewModel
import com.dixitpatel.biirr.utils.MockUtil
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailViewActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun verifyDetailViewActivityInjection() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailViewActivity::class.java)
        intent.putExtra(DetailViewActivity.SELECTION_BEER_MODEL, MockUtil.mockBeerModel())
        ActivityScenario.launch<DetailViewActivity>(intent).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity ->
                assertThat(activity.selectedBeerObj).isNotNull()

                val output = SpannableStringBuilder()
                    .bold { append(activity.baseContext.getString(R.string.name_string)) }
                    .append(if (activity.selectedBeerObj?.name.isNullOrEmpty()) "" else " ${activity.selectedBeerObj?.name}")

                assertThat(activity.binding.tvBeerName.text.toString()).isEqualTo(output.toString())
            }
        }
    }

}