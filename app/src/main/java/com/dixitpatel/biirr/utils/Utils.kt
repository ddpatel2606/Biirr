package com.dixitpatel.biirr.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * Common utils methods
 */
class Utils
{
    companion object {

        @SuppressLint("NewApi")
        fun isNetworkAvailable(context: Context): Boolean
        {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            var isNetworkAvail = false
            try {
                val n = cm?.activeNetwork
                    val nc = cm?.getNetworkCapabilities(n)
                    isNetworkAvail = nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    return isNetworkAvail
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return isNetworkAvail
        }

        fun hideKeyboard(activity: Activity, flags: Int) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val viewWithFocus = activity.currentFocus
            val windowToken = viewWithFocus?.windowToken
            if (windowToken != null) {
                imm.hideSoftInputFromWindow(windowToken, flags)
            }
        }

        /**
         * +     * <h2>dateFormatter</h2>
         * +     * This method is used to convert the date format to MMM dd, hh:mm a
         * +     *
         *
         *
         * +     *     Convert the date from yyyy-MM-dd HH:mm to MMM dd, hh:mm a
         * +     *
         * +     * @param dateToBeConverted input the date to be converted
         * +     * @return returns the Converted date
         * +     2012-03-21 20:06:46
         */
        fun dateFormatter(dateToBeConverted: String?): String? {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
            val fort = SimpleDateFormat("MMM dd yyyy, hh:mm a", Locale.US)
            val date: Date?
            try {
                if (dateToBeConverted != null) {
                    date = formatter.parse(dateToBeConverted)
                    return fort.format(date!!)
                }
            } catch (e: Exception) {
            }
            return ""
        }

        @JvmOverloads
        fun hideKeyboard(fragment: Fragment, flags: Int = InputMethodManager.HIDE_NOT_ALWAYS) {
            val activity: Activity? = fragment.activity
            if (activity != null) {
                hideKeyboard(activity, flags)
            }
        }

        fun showSoftKeyboard(me: Activity, edt: EditText?) {
            try {
                val imm = me.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}