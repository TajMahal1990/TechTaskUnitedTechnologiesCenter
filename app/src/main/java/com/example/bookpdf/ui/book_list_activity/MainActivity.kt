package com.example.bookpdf.ui.book_list_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookpdf.R
import com.example.bookpdf.data.model.BooksModel
import com.example.bookpdf.data.model.HomeModel
import com.example.bookpdf.databinding.ActivityMainBinding
import com.example.bookpdf.ui.book_list_activity.adapter.HomeAdapter
import com.example.bookpdf.ui.book_list_activity.adapter.LAYOUT_BOD

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val activity = this
    val list: ArrayList<HomeModel> = ArrayList()
    val adapter = HomeAdapter(list, activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            mRvHome.adapter = adapter
            val tempList = ArrayList<BooksModel>()
            val tempModel =
                BooksModel(
                    image = "https://thumbs.dreamstime.com/b/harry-potter-warner-brothers-studio-tour-london-uk-entrance-where-filmed-actual-film-series-movie-poster-sorcerers-164168768.jpg",
                    title = "Harry Potter",
                    description = "any desc",
                    author = "J.K.Rowling",
                    bookPDF = "https://docenti.unimc.it/antonella.pascali/teaching/2018/19055/files/ultima-lezione/harry-potter-and-the-philosophers-stone"
                )
            for (i in 1..5) {
                tempList.add(
                    tempModel
                )
            }
            list.add(
                HomeModel(catTitle = null, booksList = null, bod = null, LAYOUT_TYPE = LAYOUT_BOD)
            )
            list.add(
                HomeModel(
                    catTitle = "Motivation",
                    booksList = tempList
                )
            )
            list.add(
                HomeModel(
                    catTitle = "Fantasy",
                    booksList = tempList
                )
            )

        }

    }

}