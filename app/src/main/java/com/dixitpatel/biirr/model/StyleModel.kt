package com.dixitpatel.biirr.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Beer Style model: all data of beer.
 */
@Parcelize
data class StyleModel(  @SerializedName("category")
                   @Expose val category: CategoryModel,
                   @SerializedName("name")
                   @Expose val name: String,
                   @SerializedName("description")
                   @Expose val description: String,
                   @SerializedName("ibuMin")
                   @Expose val ibuMin: Float?,
                   @SerializedName("ibuMax")
                   @Expose val ibuMax: Float?,
                   @SerializedName("abvMin")
                   @Expose val abvMin: Float?,
                   @SerializedName("abvMax")
                   @Expose val abvMax: Float?
) : Parcelable