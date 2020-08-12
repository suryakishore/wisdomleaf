package com.example.wisdomleaf

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daytoday.searchreponse.PicSumPhotosResponse
import com.example.wisdomleaf.Constants.LIMIT
import com.example.wisdomleaf.Constants.PAGE
import com.example.wisdomleaf.Constants.SUCCESS
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.Type


/**
 * view model class for the main activity.
 */
class MainViewModel() : ViewModel() {

    private var mPhotosData = MutableLiveData<ArrayList<PicSumPhotosResponse>>()
    private var networkService: NetworkService? = null

    public fun initializeRetrofit() {

        networkService = NetworkManager.initializeBaseUrlRetrofit()


    }

    /**
     * This method used to get the search Data.
     */
    public fun getPicSumPhotos() {
        networkService.also { it ->
            it!!.getPhotos(
                PAGE, LIMIT
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (it != null) {
                        try {
                            val jsonObject: JSONObject
                            val code = it.code()
                            if (code == SUCCESS) {
                                val response: String = it.body()!!.string()
                                val gson = Gson()
                                val listType: Type = object :
                                    TypeToken<List<PicSumPhotosResponse?>?>() {}.type
                                val posts: ArrayList<PicSumPhotosResponse> =
                                    gson.fromJson(response, listType)
                                if (posts != null) {
                                    mPhotosData.postValue(posts)
                                }
                            } else {
                                jsonObject = JSONObject(it.errorBody()!!.string())
                                mPhotosData.postValue(null)
                            }
                        } catch (e: JSONException) {
                            mPhotosData.postValue(null)
                        } catch (e: IOException) {
                            mPhotosData.postValue(null)
                        }
                    }
                }
        }
    }


    /**
     * notify activity picsum photos data comes
     */
    fun onPhotosData(): MutableLiveData<ArrayList<PicSumPhotosResponse>> {
        return mPhotosData
    }


}