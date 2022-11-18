package com.xilonet.signa.model

import android.content.Context
import android.util.Log
import android.widget.Toast


class VideoFilesManager(ctxt: Context){
    //TODO: Change this for a normal set? For performance purposes?
    private val _allVideos: HashSet<LSMVideo> = hashSetOf()
    private val _categories: HashSet<CategoryWithVideos> = hashSetOf()

    init {
        ctxt.assets.list("lsm")?.forEach {
            val videos = ctxt.assets.list("lsm/$it")?.map {
                    videoFullName -> LSMVideo(videoFullName.substring(0, videoFullName.length-4),
                                                it, "lsm/$it/$videoFullName") }
            _categories.add(CategoryWithVideos(it, videos ?: listOf()))
        }

        _categories.forEach {
            it.videos.forEach {
                _allVideos.add(it)
            }
        }
    }

    fun search(query: String) : List<LSMVideo> {
        return _allVideos.filter {it.name.lowercase().contains(query.lowercase())}.sortedBy{
            it.name
        }
    }

    fun getCategoryNames() : List<String> {
        return _categories.map {it.name}.sorted()
    }

    fun getVideosOfCategory(category: String) : List<LSMVideo> {
        return _categories.find { it.name == category }?.videos?.sortedBy { it.name } ?: listOf()
    }
}

data class LSMVideo(val name: String, val category: String, val path: String)
data class CategoryWithVideos(val name: String, val videos: List<LSMVideo>)