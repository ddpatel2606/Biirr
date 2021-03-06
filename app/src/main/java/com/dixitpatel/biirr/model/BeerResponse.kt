package com.dixitpatel.biirr.model

import androidx.annotation.VisibleForTesting
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *  Beer Listing Result.
 */
@VisibleForTesting
data class BeerResponse(
  @SerializedName("currentPage")
  @Expose val currentPage: Int,
  @SerializedName("numberOfPages")
  @Expose val numberOfPages: Int,
  @SerializedName("totalResults")
  @Expose val totalResults: String?,
  @SerializedName("status")
  @Expose val status: String?,
  @SerializedName("data")
  @Expose val data: ArrayList<BeerModel?>
)
