package com.example.bookpdf.utils

import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.loadOnline(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .transition(withCrossFade())
        .thumbnail(0.5f)
        .into(this)
}
