package com.dixitpatel.biirr.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Label model show image data.
 */
@Parcelize
data class LabelsModel(
              @SerializedName("medium")
              @Expose val mediumIcon : String?,
              @SerializedName("contentAwareMedium")
              @Expose val contentAwareMedium: String?) : Parcelable