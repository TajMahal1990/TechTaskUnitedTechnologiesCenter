package com.example.bookpdf.ui.details_activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.bookpdf.R
import com.example.bookpdf.data.model.BooksModel
import com.example.bookpdf.data.repository.BookRepo
import com.example.bookpdf.databinding.ActivityDetailsBinding
import com.example.bookpdf.databinding.LayoutProgressBinding
import com.example.bookpdf.domain.MyResponses
import com.example.bookpdf.ui.details_activity.view_model.BookViewModel
import com.example.bookpdf.ui.details_activity.view_model.BookViewModelFactory
import com.example.bookpdf.ui.pdf_activity.PdfActivity
import com.example.bookpdf.utils.loadOnline

class DetailsActivity : AppCompatActivity() {
    val activity = this
    lateinit var binding: ActivityDetailsBinding

    private val repo = BookRepo(activity)
    private val viewModel by lazy {
        ViewModelProvider(
            activity,
            BookViewModelFactory(repo)
        )[BookViewModel::class.java]
    }
    private val TAG = "Details_Activity"

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
                viewModel.downloadFile(bookModel.bookPDF, "${bookModel.title}.pdf")

            }

            val dialogBinding = LayoutProgressBinding.inflate(layoutInflater)
            val dialog = Dialog(activity).apply {
                setCancelable(false)
                setContentView(dialogBinding.root)
            }

            viewModel.downloadLiveData.observe(activity) {
                when (it){
                    is MyResponses.Error -> {
                        dialog.dismiss()
                        Log.e(TAG, "onCreate: ${it.errorMessage}")

                    }
                    is MyResponses.Loading -> {
                        dialog.show()
                        Log.i(TAG, "onCreate: Progress ${it.progress}")
                    }
                    is MyResponses.Success -> {
                        dialog.dismiss()
                        Log.i(TAG, "onCreate: Downloaded ${it.data}")
                        Intent().apply {
                            putExtra("bookPdf",it.data?.filePath)
                            setClass(activity,PdfActivity::class.java)
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }
}