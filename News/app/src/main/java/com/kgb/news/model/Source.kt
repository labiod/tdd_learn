package com.kgb.news.model

import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/**
 * @author Krzysztof Betlej <labiod@wp.pl>.
 * @date 5/23/18
 */
@Parcelize
class Source : Parcelable {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}
