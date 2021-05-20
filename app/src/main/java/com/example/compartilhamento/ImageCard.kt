package com.example.compartilhamento

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageCard(var img_title: String, var img_uri: Int):Parcelable