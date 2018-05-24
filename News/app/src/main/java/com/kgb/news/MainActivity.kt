package com.kgb.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.kgb.news.model.GetArticlesResponse
import com.kgb.news.networking.NewsAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_activity_main.layoutManager = LinearLayoutManager(this)

        val call: Call<GetArticlesResponse> = NewsAPI.getApi().getArticles("reuters", "top")
        call.enqueue(object: Callback<GetArticlesResponse> {
            override fun onResponse(call: Call<GetArticlesResponse>?, response: Response<GetArticlesResponse>?) {
                response?.body()?.articles?.let {
                    Toast.makeText(this@MainActivity, "Response received", Toast.LENGTH_SHORT).show()
                    NewsStore.newsArticles = it
                    val homeNewsAdapter: HomeNewsAdapter = HomeNewsAdapter(it)
                    rv_activity_main.adapter = homeNewsAdapter
                    showNewsApiSnack()
                }

            }

            override fun onFailure(call: Call<GetArticlesResponse>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error received", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showNewsApiSnack() {
        Snackbar.make(coordinator_layout, "Powered by NewsApi.org", Snackbar.LENGTH_LONG)
                .setAction("Visit", {
                    loadNewsApiWebside()
                })
                .show()
    }

    private fun loadNewsApiWebside() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org")))
    }
}
