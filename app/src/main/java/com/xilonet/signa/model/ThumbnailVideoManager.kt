package com.xilonet.signa.model

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.remember
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource

class ThumbnailVideoManager(context: Context) {

    // Declaring ExoPlayer
    val exoPlayer =
        ExoPlayer.Builder(context).build().apply {
            val dataSourceFactory = DefaultDataSource.Factory(context)
            /*
            val source = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                MediaItem.fromUri(videoUri)
            )
            setMediaSource(source)
            prepare()
            */
        }
    val SAMPLE_VIDEO_URI = Uri.parse("asset:///lsm/LSM_Comida_Web/Chocolate_Web.m4v")
}