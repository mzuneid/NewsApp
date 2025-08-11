package com.mzdev.newsapp.util

import android.text.format.DateUtils
import java.time.OffsetDateTime
import java.time.ZoneId

fun getRelativeTime(publishTime: String): String {
    val odt = OffsetDateTime.parse(publishTime)
    val millis = odt.atZoneSameInstant(ZoneId.systemDefault()).toInstant().toEpochMilli()
    return DateUtils.getRelativeTimeSpanString(millis, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString()
}
