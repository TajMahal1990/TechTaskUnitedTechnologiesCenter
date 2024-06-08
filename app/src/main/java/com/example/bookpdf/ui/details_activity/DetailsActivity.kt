package com.example.bookpdf.ui.details_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookpdf.R
import com.example.bookpdf.data.model.BooksModel
import com.example.bookpdf.databinding.ActivityDetailsBinding
import com.example.bookpdf.ui.pdf_activity.PdfActivity
import com.example.bookpdf.utils.loadOnline

class DetailsActivity : AppCompatActivity() {
    val activity = this
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bookModel = intent.getSerializableExtra("book_model") as BooksModel

        binding.apply {
            bookModel.apply {
                mBookTitle.text = title
                mAuthorName.text = author
                mBookDesc.text = description
                mBookImage.loadOnline(image)

            }
            mReadBookBtn.setOnClickListener {
                val intent = Intent()

                intent.setClass(activity, PdfActivity::class.java)
                startActivity(intent)
            }
        }
    }
}