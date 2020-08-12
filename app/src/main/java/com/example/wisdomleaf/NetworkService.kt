package com.example.wisdomleaf

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface was used to call the list api to get the results from server.
 */
interface NetworkService {
    @GET("list")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Observable<Response<ResponseBody>>
}