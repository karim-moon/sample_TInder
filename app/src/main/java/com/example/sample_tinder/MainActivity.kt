package com.example.sample_tinder

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.UserDictionary
import android.util.Log
import android.widget.Gallery
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycle_layout.*
import java.io.File
import com.example.sample_tinder.ImageDataClass as ImageDataClass

class MainActivity : AppCompatActivity() {
    companion object{
        val myDataSet = Array<String>(10, {i -> i.toString() })
    }
    val dataList : MutableList<ImageDataClass> = mutableListOf<ImageDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customAdapter = CustomAdapter(dataList).also{
            recycle.adapter = it
        }

        val myLayoutManager = LinearLayoutManager(this,VERTICAL,true).also {
            recycle.layoutManager = it
        }

        val commit = mutableListOf<ImageDataClass>()
        val myRes = MyContent(this).myResolver?.use {
            val displayNameCol = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME)
            val dataCol = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)
            val dateTaken = it.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN)
            it.moveToFirst()


            while(it.moveToNext()){
                val displayName = it.getString(displayNameCol)
                val data = it.getString(dataCol)
                val date = it.getLong(dateTaken)

                dataList += ImageDataClass(displayName, data)
            }
        }


//        val path = dataList.filterIndexed { i, it ->
//            File(it.imagePath).exists().also { bool ->
//                if (bool) {
//                    Log.d("문종3",  "$i, $it")
//                }
//            }
//        }.map {
//            File(it.imagePath).absolutePath
//        }.first()

//        val testIma =
//            BitmapFactory.decodeFile(path)
////        Log.d("문종","$testIma")
////        Log.d("문종2","$path")
//        testImaView.setImageBitmap(testIma)

//        Glide.with(this)
//            .asBitmap()
//            .load(path)
//            .addListener(object : RequestListener<Bitmap> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Bitmap>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Log.d("문종2", "$e")
//                    return false
//                }
//
//                override fun onResourceReady(
//                    resource: Bitmap?,
//                    model: Any?,
//                    target: Target<Bitmap>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Log.d("문종3", "$resource")
//                    return false
//                }
//
//            })
//            .into(recycleImage)



        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder(this)
                    .setTitle("hey man please allow this")
                    .setMessage("hello man")
                    .setNeutralButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        requestPermissions(
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            100
                        )
                    }.apply { setCancelable(false) }
                    .show()
            } else {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            }
        }
    }
}
