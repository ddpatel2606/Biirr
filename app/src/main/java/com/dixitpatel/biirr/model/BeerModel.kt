package com.dixitpatel.biirr.model

import android.os.Parcelable
import androidx.annotation.VisibleForTesting
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

/**
 *  Beer object with all necessary parameters
 */
@VisibleForTesting
@Parcelize
data class BeerModel(
        @SerializedName("id")
        @Expose val id: String,
        @SerializedName("name")
        @Expose val name: String,
        @SerializedName("abv")
        @Expose val abv: String?,
        @SerializedName("ibu")
        @Expose val ibu: String? = "",
        @SerializedName("description")
        @Expose val description: String? = "",
        @SerializedName("labels")
        @Expose val labels: LabelsModel?,
        @SerializedName("available")
        @Expose val available: AvailableModel?,
        @SerializedName("style")
        @Expose val style: StyleModel?,
        @SerializedName("createDate")
        @Expose val createDate: String?
) : Parcelable

