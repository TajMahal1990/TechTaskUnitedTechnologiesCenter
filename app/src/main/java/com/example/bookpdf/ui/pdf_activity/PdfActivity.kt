package com.example.bookpdf.ui.pdf_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.example.bookpdf.databinding.ActivityPdfBinding
import java.io.IOException

class PdfActivity : AppCompatActivity() {
    lateinit var binding: ActivityPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfBinding.inflate(layoutInflater)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()

        val bookPdf = intent.getStringExtra("bookPdf")
        if (bookPdf != null) {
            try {
                binding.pdfView.fromAsset(bookPdf)
                    .swipeHorizontal(true)
                    .enableSwipe(true)
                    .pageSnap(true)
                    .autoSpacing(true)
                    .pageFling(true)
                    .onPageChange { page, pageCount ->
                        binding.pageNumberText.text = "Page ${page + 1} of $pageCount"
                    }
                    .load()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
        }
    }
}
