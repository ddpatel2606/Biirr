package com.dixitpatel.biirr.repository

import androidx.lifecycle.MutableLiveData
import com.dixitpatel.biirr.constant.API_KEY
import com.dixitpatel.biirr.model.BeerResponse
import com.dixitpatel.biirr.network.APIRequestResponseHandler
import com.dixitpatel.biirr.network.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 *  MainViewRepository class to call Main beer API.
 */
class MainViewRepository @Inject constructor() : Repository
{
    private val beerApiResponse = MutableLiveData<APIRequestResponseHandler<BeerResponse?>>()

    fun beerApiResult(): MutableLiveData<APIRequestResponseHandler<BeerResponse?>> = beerApiResponse

    // beer listing API
    fun beerApiCall(page:Int, apiInterface : ApiInterface)
    {
        beerApiResponse.value = APIRequestResponseHandler.loading(null)

        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<BeerResponse> = apiInterface.fetchBeerList(API_KEY,page)

            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            beerApiResponse.value = APIRequestResponseHandler.success(it)
                        }
                    } else {
                        beerApiResponse.value = APIRequestResponseHandler.error(null,response.errorBody().toString())
                        Timber.e(response.errorBody().toString())
                    }
                }
            } catch (e: HttpException) {
                Timber.e("Exception ${e.message}")
            } catch (e: Throwable) {
                Timber.e("Exception ${e.message}")

            }
        }
    }
}