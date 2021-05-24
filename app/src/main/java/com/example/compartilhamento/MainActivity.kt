package com.example.compartilhamento

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.content.ContentUris
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ImageAdapter.OnItemClickListener {

    companion object{

        const val REQUEST_CODE = 1


    }


//    private var imgList = ArrayList<ImageCard>()
//        private var imgList = getImages()
//    private var adapter = ImageAdapter(imgList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions()

        var imgList = getImages()
        var adapter = ImageAdapter(imgList, this)
        comp_recycler_view.adapter = adapter
        comp_recycler_view.layoutManager = GridLayoutManager(this, 2)
        comp_recycler_view.setHasFixedSize(true)

//        getImages()

    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

//    private fun generateImgList(size: Int): ArrayList<ImageCard> {
//        val list = ArrayList<ImageCard>()
//        for (i in (size-1) downTo  0) {
//            val item = ImageCard("Android ${i}", R.drawable.ic_android_black_24dp)
//            list += item
//        }
//        return list
//    }

    private fun getImages(): ArrayList<ImageCard>   {
        val list = ArrayList<ImageCard>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED
        )
        val selection = null
        val selectionArgs = null
        val sortOrder = null

        applicationContext.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(nameColumn)
                Log.i("fon", "Media ID: $id")
                Log.i("fon", "Title: $title")

                val contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                Log.i("fon", "Media ID: $contentUri")

                val item = ImageCard(title, contentUri)
                list += item
            }
        }
        return list
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE,
            )
    }

    override fun onRequestPermissionsResult(requestCode: Int,
        permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_DENIED
        ) { Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show() }
    }
}


