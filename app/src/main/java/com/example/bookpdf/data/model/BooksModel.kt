package com.example.bookpdf.data.model

import java.io.Serializable

data class BooksModel(
    val image: String,
    val title: String,
    val description: String,
    val author: String,
    val bookPDF: String,
):Serializable

