package com.kgb.news.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Krzysztof Betlej <labiod@wp.pl>.
 * @date 5/23/18
 */
@Parcelize
class GetArticlesResponse : Parcelable {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null
    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
}