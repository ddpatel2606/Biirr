package com.dixitpatel.biirr.ui.detail

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.dixitpatel.biirr.R
import com.dixitpatel.biirr.utils.Utils
import javax.inject.Inject

/**
 *  Detail Activity ViewModel : ViewModel
 */
class DetailViewModel @Inject constructor() : ViewModel()
{
    companion object {

        // Bind Beer name with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("beer_name")
        fun TextView.setBeerName(name: String?) {

            val output = SpannableStringBuilder()
                    .bold { append(context.getString(R.string.name_string)) }
                    .append(if (name.isNullOrEmpty()) "" else " $name")
            this.text = output

        }

        // Bind Beer Desc with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("beer_desc")
        fun TextView.setBeerDesc(desc: String?) {

            val output = SpannableStringBuilder()
                    .bold { append(context.getString(R.string.desc_string)) }
                    .append(if (desc.isNullOrEmpty()) "" else " $desc")
            this.text =output

        }

        // Bind Beer Desc with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("beer_availability")
        fun TextView.setBeerAvailability(available: String?) {

            val output = SpannableStringBuilder()
                    .bold { append(context.getString(R.string.available)) }
                    .append(if (available.isNullOrEmpty()) "" else " $available")
            this.text =output

        }

        // Bind Beer ABV  with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("beer_abv")
        fun TextView.setBeerABV(abvText: String?) {

            val output = SpannableStringBuilder()
                    .bold { append(context.getString(R.string.abv_string)) }
                    .append(if (abvText.isNullOrEmpty()) "" else " ${"$abvText %"}")
            this.text = output

        }

        // Bind Beer Test  with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("beer_test")
        fun TextView.setBeerTest(test: String?) {

            val output = SpannableStringBuilder()
                    .bold { append(context.getString(R.string.ibu_string)) }
                    .append(if (test.isNullOrEmpty()) "" else " $test (${checkBitternessBeer(test)})")
            this.text = output

        }

        // Checks the beer test is smooth, bitter or hipster plus.
        private fun checkBitternessBeer(ibu: String): String {
           return when (ibu.toDouble()) {
                   in 0.0..20.0 -> "Smooth"
                   in 20.0..50.0 -> "Bitter"
                   in 50.0..Double.MAX_VALUE -> "Hipster Plus"
                   else -> ""
           }
        }

        // Bind Beer Test with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("beer_created_date")
        fun TextView.setBeerCreatedDate(date: String?) {

            val output = SpannableStringBuilder()
                    .bold { append(context.getString(R.string.created_string)) }
                    .append(if (date.isNullOrEmpty()) "" else  " ${Utils.dateFormatter(date)}")
            this.text = output
        }

        // Bind Beer Category with view.
        @JvmStatic
        @SuppressLint("SetTextI18n")
        @BindingAdapter("beer_category")
        fun TextView.setBeerCategory(category: String?) {
            val output = SpannableStringBuilder()
                    .bold { append(context.getString(R.string.category_string)) }
                    .append(if (category.isNullOrEmpty()) "" else " $category")
            this.text = output
        }
    }

}