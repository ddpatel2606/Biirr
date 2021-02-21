package com.dixitpatel.biirr.ui.main

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.dixitpatel.biirr.R
import com.dixitpatel.biirr.constant.DEFAULT_IMAGE
import com.dixitpatel.biirr.repository.MainViewRepository
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 *  Main Activity ViewModel : ViewModel
 */
class MainActivityViewModel @Inject constructor(val mainViewRepository: MainViewRepository) : ViewModel()
{
    companion object {

        // load image bind with Imageview.
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(image: ImageView, url: String?) {

                Picasso.get()
                    .load(url ?: DEFAULT_IMAGE)
                    .placeholder(R.drawable.icon_loading_place_holder)
                    .into(image)
        }

    }

}