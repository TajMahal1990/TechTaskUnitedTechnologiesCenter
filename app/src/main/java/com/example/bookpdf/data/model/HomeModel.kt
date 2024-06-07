package com.example.bookpdf.data.model

import com.example.bookpdf.ui.book_list_activity.adapter.LAYOUT_HOME

data class HomeModel(
    val catTitle:String?=null,
    val booksList:ArrayList<BooksModel>?=null,

    val bod:BooksModel?=null,
    val LAYOUT_TYPE:Int = LAYOUT_HOME
)
