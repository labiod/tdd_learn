package com.kgb.news.utils

import android.util.Log
import com.google.firebase.crash.FirebaseCrash
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 5/24/18
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */
class DateUtils {

    companion object {
        private val TAG: String = DateUtils::class.java.simpleName
        fun formatNewsApiDate(inputDate: String): String {
            try {
                val inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
                val outputDateFormat = "EEE, d MMM yyyy KK:mm"
                val dateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
                val date = dateFormat.parse(inputDate)
                dateFormat.applyPattern(outputDateFormat)
                return dateFormat.format(date)
            } catch(e: ParseException) {
                Log.e(TAG, "date parse exception:", e)
                FirebaseCrash.report(e)
            }
            return inputDate
        }
    }
}