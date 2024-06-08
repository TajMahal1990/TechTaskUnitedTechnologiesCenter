package com.example.bookpdf.ui.details_activity.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookpdf.data.repository.BookRepo

class BookViewModelFactory(private val repo: BookRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(repo) as T
    }
}