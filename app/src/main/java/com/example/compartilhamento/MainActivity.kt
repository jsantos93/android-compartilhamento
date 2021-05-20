package com.example.compartilhamento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ImageAdapter.OnItemClickListener {

//    private var imgList = ArrayList<ImageCard>()
    private var imgList = generateImgList(4)
    private var adapter = ImageAdapter(imgList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        comp_recycler_view.adapter = adapter
        comp_recycler_view.layoutManager = LinearLayoutManager(this)
        comp_recycler_view.setHasFixedSize(true)


    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    private fun generateImgList(size: Int): ArrayList<ImageCard> {
        val list = ArrayList<ImageCard>()
        for (i in (size-1) downTo  0) {
            val item = ImageCard("Android ${i}", R.drawable.ic_android_black_24dp)
            list += item
        }
        return list
    }
}