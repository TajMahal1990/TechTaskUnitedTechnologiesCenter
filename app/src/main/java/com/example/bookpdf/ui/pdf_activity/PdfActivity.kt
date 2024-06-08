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

        val bookPdf = intent.getStringExtra("book_pdf")
        if (bookPdf != null) {
            try {
                binding.pdfView.fromAsset(bookPdf)
                    .swipeHorizontal(false)
                    .enableSwipe(true)
                    .pageSnap(true)
                    .autoSpacing(true)
                    .pageFling(true)
                    .load()
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle error case if PDF could not be loaded
            }
        } else {
            // Handle error case if bookPdf is null
        }
    }
}
