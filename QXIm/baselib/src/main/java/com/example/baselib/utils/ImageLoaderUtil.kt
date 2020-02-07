package com.example.baselib.utils

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baselib.R
import com.example.baselib.gson.GlideCircleWithBorder

class ImageLoaderUtil {

    companion object {
        fun load(context: Context, drawable: Int, @NonNull iv: ImageView) {
            Glide.with(context).load(drawable).into(iv)
        }

        fun load(context: Context, url: String, @NonNull iv: ImageView) {
            Glide.with(context).load(url).placeholder(R.mipmap.ic_default_image).error(R.mipmap.ic_default_image)
                .into(iv)
        }

        fun load(context: Fragment, url: String, @NonNull iv: ImageView) {
            Glide.with(context).load(url).placeholder(R.mipmap.ic_default_image).error(R.mipmap.ic_default_image)
                .into(iv)
        }

        fun load(context: Activity, url: String, @NonNull iv: ImageView) {
            Glide.with(context).load(url).placeholder(R.mipmap.ic_default_image).error(R.mipmap.ic_default_image)
                .into(iv)
        }

        fun loadCircleImage(context: Context, url: String, @NonNull iv: ImageView) {
            Glide.with(context)
                .load(url)
                .apply(RequestOptions().circleCrop())
                .transform(GlideCircleWithBorder(context, 2, ContextCompat.getColor(context, R.color.white)))
                .into(iv)
        }

        fun loadCircleImage(context: Context, drawable: Int, @NonNull iv: ImageView) {
            Glide.with(context)
                .load(drawable)
                .apply(RequestOptions().circleCrop())
                .transform(GlideCircleWithBorder(context, 2, ContextCompat.getColor(context, R.color.white)))
                .into(iv)
        }

    }
}