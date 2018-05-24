package com.kgb.news

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val index = intent.getIntExtra(KEY_INDEX, -1)
        if (index != -1) {
            updateNewsDetails(index)
        } else {
            Toast.makeText(this, "sorry incorrect index passed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun updateNewsDetails(index: Int) {
        //wv_news_details.settings.javaScriptEnabled = true
        wv_news_details.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                pb_news_details.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                pb_news_details.visibility = View.GONE
            }


            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                pb_news_details.visibility = View.GONE
                Toast.makeText(this@NewsDetailsActivity, "Error in loading webpage", Toast.LENGTH_SHORT).show()
            }
        }
        wv_news_details.loadUrl(NewsStore.newsArticles[index].url)
        supportActionBar?.title = NewsStore.newsArticles[index].title
    }

    companion object {
        private const val KEY_INDEX = "key_index"

        @JvmStatic
        fun launch(context: Context, index: Int) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }
}
