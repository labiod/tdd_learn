package com.kgb.news.networking

import com.kgb.news.model.GetArticlesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 5/23/18
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */
class NewsAPI {

    companion object {
        const val APIKEY = "d52a4a8d16b14e599dbed950c7e3c7a0"
        const val APIPATH = "https://newsapi.org/v1/"
        private var initialized: Boolean = false
        lateinit var newsService: NewsService

        fun getApi(): NewsService {
            if (!initialized) {
                val retrofit: Retrofit = Retrofit.Builder()
                        .baseUrl(APIPATH)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                newsService = retrofit.create(NewsService::class.java)
                initialized = true
            }
            return newsService
        }
    }
}

interface NewsService {

    @GET("articles?apiKey=" + NewsAPI.APIKEY)
    fun getArticles(@Query("source") source: String, @Query("sortBy") sortBy: String): Call<GetArticlesResponse>
}