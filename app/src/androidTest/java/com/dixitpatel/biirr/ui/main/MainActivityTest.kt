package com.dixitpatel.biirr.ui.main

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.registerIdlingResources
import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.dixitpatel.biirr.R
import com.dixitpatel.biirr.constant.TAG
import com.dixitpatel.biirr.network.AuthStatus
import com.dixitpatel.biirr.utils.Utils
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest
{


    @Rule
    @JvmField
    var mainActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java, false, true
    )

    @Test
    fun IsViewVisible() {
        Assert.assertTrue(mainActivityRule.activity.findViewById<View>(R.id.myRecyclerView).isVisible)
    }


    @Test
    @LargeTest
    @UiThreadTest
    fun checkAPIResponse() {

        mainActivityRule.activity.model.mainViewRepository.beerApiResult()
            .observe(mainActivityRule.activity, androidx.lifecycle.Observer
            { result ->
                when (result.status) {

                    AuthStatus.LOADING -> {
                        Timber.e("Loading API")
                    }

                    AuthStatus.ERROR -> {
                        Assert.assertFalse(result.message, false)
                    }

                    AuthStatus.SUCCESS -> {
                        Timber.e("Result API")
                        Assert.assertTrue(result.data?.data?.isNotEmpty() == true)
                    }
                }
            })

        if(Utils.isNetworkAvailable(context = mainActivityRule.activity))
        {
            mainActivityRule.activity.apiInterface.let {
                mainActivityRule.activity.model.mainViewRepository.beerApiCall(
                    1, mainActivityRule.activity.apiInterface
                ) }

        } else {
            Assert.assertFalse(
                mainActivityRule.activity.getString(R.string.internet_not_available),
                false
            )
        }

    }



}