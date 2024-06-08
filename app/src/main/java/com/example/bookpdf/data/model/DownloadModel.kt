package com.example.bookpdf.data.model

data class DownloadModel(
    var progress: Int = 0,
    var isDownloaded: Boolean,
    var downloadId: Long,
    var filePath: String
)