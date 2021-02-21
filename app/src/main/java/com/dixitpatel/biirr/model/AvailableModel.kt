package com.dixitpatel.biirr.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Check stock is available and limited.
 */
@Parcelize
data class AvailableModel(@SerializedName("name")
                          @Expose val name: String) : Parcelable