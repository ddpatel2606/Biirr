package com.dixitpatel.biirr.model

import android.os.Parcelable
import androidx.annotation.VisibleForTesting
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Beer Category Model data.
 */
@VisibleForTesting
@Parcelize
data class CategoryModel(@SerializedName("name")
                    @Expose val name: String,
                    @SerializedName("createDate")
                    @Expose val createDate: String) : Parcelable {
}