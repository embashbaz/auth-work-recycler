package com.example.authflow

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.authflow.domain.model.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
@JvmField
val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Upload image in the background"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "show upload status"
const val CHANNEL_ID = "UPLOAD_IMAGE_NOTIFICATION"
const val NOTIFICATION_ID = 1

fun <T> Fragment.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

fun <T> AppCompatActivity.collectLatestLifecycleFlowActivity(
    flow: Flow<T>,
    collect: suspend (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

fun makeStatusNotification(message: String, context: Context) {


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        // Add the channel
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    // Create the notification
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Image Upload")
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setOnlyAlertOnce(true)
        //.setProgress()
        .setVibrate(LongArray(0))

    // Show the notification
    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}

fun <T> getPages(c: Collection<T>?, pageSize: Int?): List<List<T>>? {
    var pageSize = pageSize
    if (c == null) return Collections.emptyList()
        val list: List<T> = ArrayList(c)
    if (pageSize == null || pageSize <= 0 || pageSize > list.size) pageSize = list.size
        val numPages = Math.ceil(list.size.toDouble() / pageSize as Double).toInt()
    val pages: MutableList<List<T>> = ArrayList(numPages)
    var pageNum = 0

    while (pageNum < numPages) {
        pages.add(list.subList((pageNum* pageSize as Int), Math.min((++pageNum * pageSize as Int), list.size)))
    }
    return pages
}

fun <T> getPage(sourceList: List<T>?, page: Int, pageSize: Int): List<T>? {
    require(!(pageSize <= 0 || page <= 0)) { "invalid page size: $pageSize" }
    val fromIndex = (page - 1) * pageSize
    return if (sourceList == null || sourceList.size <= fromIndex) {
        Collections.emptyList()
    } else sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size))

    // toIndex exclusive
}

fun generateProducts(): List<Products>{
    var products = mutableListOf<Products>()
    products.add(Products("Electronics"))
    products.add(Products("smart home"))
    products.add(Products("art"))
    products.add(Products("craft"))
    products.add(Products("automotive"))
    products.add(Products("baby"))
    products.add(Products("beauty"))
    products.add(Products("fashion"))
    products.add(Products("health"))
    products.add(Products("household"))
    products.add(Products("home"))
    products.add(Products("Kitchen"))
    products.add(Products("industrial"))
    products.add(Products("luggage"))
    products.add(Products("movies"))
    products.add(Products("pet"))
    products.add(Products("software"))
    products.add(Products("sport"))
    products.add(Products("tools"))
    products.add(Products("toys"))
    products.add(Products("video games"))


    for (product in products){
        (1..30).forEachIndexed { index, c ->
            product.productList.add(c.toString())
        }

        product.productList.shuffle()
    }

    return products
}