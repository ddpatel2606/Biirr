package com.dixitpatel.biirr.model

import android.os.Parcelable
import androidx.annotation.VisibleForTesting
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Check stock is available and limited.
 */
@VisibleForTesting
@Parcelize
data class AvailableModel(@SerializedName("name")
                          @Expose val name: String) : Parcelable