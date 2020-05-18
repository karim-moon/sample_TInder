package com.example.sample_tinder

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.recycle_layout.view.*
import java.io.File


class CustomAdapter(private val data :MutableList<ImageDataClass>): Adapter<CustomAdapter.MyViewHolder>() {


    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        init {
            val textView : TextView = view.findViewById(R.id.textA)
            val imageView : ImageView = view.findViewById(R.id.recycleImage)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_layout,parent,false)


        return MyViewHolder(view)
    }


    override fun getItemCount() = data.size


    override fun onBindViewHolder(myViewHolder : MyViewHolder, position: Int) {
        val path = data.filterIndexed { i, it ->
            File(it.imagePath).exists().also { bool ->
                if (bool) {
                    Log.d("문종3",  "$i, $it")
                }
            }
        }.map {
            File(it.imagePath).absolutePath
        }.first()

        val testIma =
                BitmapFactory.decodeFile(path)
//              myViewHolder.view.textA.text = data[position]
        myViewHolder.view.recycleImage.setImageBitmap(testIma)
    }

}
