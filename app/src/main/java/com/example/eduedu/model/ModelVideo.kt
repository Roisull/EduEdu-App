package com.example.eduedu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ModelVideo(
    var title: String? = null,
    var price: Int = 0,
    var imageVideo: Int = 0,
    var imageRating: Int = 0
):Parcelable