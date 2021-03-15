package com.dixitpatel.biirr.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.dixitpatel.biirr.R
import com.dixitpatel.biirr.constant.DEFAULT_IMAGE
import com.dixitpatel.biirr.databinding.ActivityDetailViewBinding
import com.dixitpatel.biirr.extension.extraNotNull
import com.dixitpatel.biirr.model.BeerModel
import com.dixitpatel.biirr.network.ApiInterface
import com.dixitpatel.biirr.network.AuthStatus
import com.dixitpatel.biirr.ui.base.BaseActivity
import com.dixitpatel.biirr.utils.Alerts
import com.dixitpatel.biirr.utils.Utils
import com.github.florent37.picassopalette.PicassoPalette
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import java.lang.Exception

import javax.inject.Inject

/**
 *  Detail Activity : Detail View of beer
 *  It will open after clicking on beer Item
 */

@AndroidEntryPoint
class DetailViewActivity : BaseActivity()
{
    val binding: ActivityDetailViewBinding by binding(R.layout.activity_detail_view)

    companion object
    {
         const val SELECTION_BEER_MODEL = "selection_beer_model"
         const val EXTRA_IMAGE_TRANSITION_NAME = "image_trans"
    }

    val selectedBeerObj : BeerModel? by extraNotNull(SELECTION_BEER_MODEL)

    @Inject
    lateinit var apiInterface : ApiInterface

    private val models: DetailViewModel by viewModels()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        binding.viewModel = models

        binding.toolbar.let {
            setSupportActionBar(it)
                    it.setNavigationOnClickListener { onBackPressed() }
                            it.elevation = 0F
            supportActionBar.let { its ->
                its?.setDisplayHomeAsUpEnabled(true)
                its?.setHomeButtonEnabled(true)
                its?.setHomeAsUpIndicator(R.drawable.ic_back_material)
            }
        }


            binding.toolbar.title = selectedBeerObj?.name
            supportActionBar?.title = selectedBeerObj?.name

            binding.ivbeerHeader.transitionName = intent.getStringExtra(EXTRA_IMAGE_TRANSITION_NAME)

            // Show Image With transition
             Picasso.get()
                    .load(selectedBeerObj?.labels?.mediumIcon ?: DEFAULT_IMAGE)
                    .into(binding.ivbeerHeader,object: Callback{
                        override fun onSuccess() {
                            supportStartPostponedEnterTransition()
                        }
                        override fun onError(e: Exception?) {
                            supportStartPostponedEnterTransition()
                        }
                    })

            binding.data = selectedBeerObj
            binding.executePendingBindings()


        binding.appBar.run {
            outlineProvider = null
            elevation = 0F
        }
        binding.toolbarLayout.run{
            elevation = 0F
            setCollapsedTitleTextColor(ContextCompat.getColor(this@DetailViewActivity, R.color.color_primary))
        }
    }


}

