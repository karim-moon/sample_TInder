package com.example.sample_tinder

import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import java.text.SimpleDateFormat

val projection = arrayOf(
    MediaStore.Images.ImageColumns.DISPLAY_NAME,
    MediaStore.Images.ImageColumns.DATA,
    MediaStore.Images.ImageColumns.DATE_TAKEN
)
val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
val selectionArgs = arrayOf(
    dateToTimeStamp(date = 1, month = 1, year = 2019).toString()
)
val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"


fun dateToTimeStamp( date : Int, month : Int, year : Int): Long {

    return SimpleDateFormat("dd.MM.yyyy").let{it ->
        it.parse("$date.$month.$year")?.time ?:0
    }

}

class MyContent (context: Context) {
    val myResolver = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        selectionArgs,
        sortOrder
    )

}



