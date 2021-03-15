package com.dixitpatel.biirr.model

import android.os.Parcelable
import androidx.annotation.VisibleForTesting
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Label model show image data.
 */
@VisibleForTesting
@Parcelize
data class LabelsModel(
              @SerializedName("medium")
              @Expose val mediumIcon : String?,
              @SerializedName("contentAwareMedium")
              @Expose val contentAwareMedium: String?) : Parcelable