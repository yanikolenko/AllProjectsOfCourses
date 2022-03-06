package com.yaroslavnikolenko.quakereload.AdditionalClasses

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import com.yaroslavnikolenko.quakereload.R

fun addTextToBitmap(
    context: Context,
    magnitude: Double
): Bitmap {
    val resources: Resources = context.resources

    var bitmap = BitmapFactory.decodeResource(resources, R.drawable.basic_marker_back)
    var bitmapConfig = bitmap.config

    if (bitmapConfig == null) {
        bitmapConfig = Bitmap.Config.ARGB_8888
    }

    bitmap = bitmap.copy(bitmapConfig, true)
    val canvas = Canvas(bitmap)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    paint.color = context.getColor(getLevel(magnitude).backgroundColor)

    paint.textSize = 53f
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

    val bounds = Rect()

    paint.getTextBounds(magnitude.toString(), 0, magnitude.toString().length, bounds)
    val x: Int = (bitmap.width - bounds.width()) / 2
    val y: Int = (bitmap.height + bounds.height()) / 2
    canvas.drawText(magnitude.toString(), x.toFloat(), y.toFloat(), paint)

    return bitmap
}
