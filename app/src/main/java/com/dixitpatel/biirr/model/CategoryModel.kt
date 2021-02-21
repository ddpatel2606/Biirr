package com.dixitpatel.biirr.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Beer Category Model data.
 */
@Parcelize
data class CategoryModel(@SerializedName("name")
                    @Expose val name: String,
                    @SerializedName("createDate")
                    @Expose val createDate: String) : Parcelable {
}