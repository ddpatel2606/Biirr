package com.dixitpatel.biirr.network

import com.dixitpatel.biirr.constant.API_KEY
import com.dixitpatel.biirr.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *  All Network Calling Apis are define here with Coroutine function.
 */
interface ApiInterface {

    // Fetch Beer list API
    // http://api.brewerydb.com/v2/beers/?key=13d7fdca22cbc95434f3f65d7be4a5a9&p=2
    @GET("beers")
    suspend fun fetchBeerList(@Query("key") key: String = API_KEY,
                              @Query("p") page: Int = 0): Response<BeerResponse>


}