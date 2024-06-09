package com.example.bookpdf.ui.pdf_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.example.bookpdf.databinding.ActivityPdfBinding
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import java.io.IOException

class PdfActivity : AppCompatActivity(), OnPageChangeListener {
    lateinit var binding: ActivityPdfBinding
    private val PREFS_NAME = "PDFPrefs"
    private val PREF_PAGE_KEY = "lastReadPage"
    private val PREF_FILE_KEY = "lastReadFile"
    private var bookPdf: String? = null
    private var lastReadPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfBinding.inflate(layoutInflater)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()

        val sharedPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        bookPdf = intent.getStringExtra("bookPdf")
        if (bookPdf != null) {
            lastReadPage = sharedPrefs.getInt("$PREF_PAGE_KEY-$bookPdf", 0)
            try {
                binding.pdfView.fromAsset(bookPdf!!)
                    .swipeHorizontal(true)
                    .enableSwipe(true)
                    .pageSnap(true)
                    .autoSpacing(true)
                    .pageFling(true)
                    .onPageChange(this)
                    .defaultPage(lastReadPage)
                    .load()
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle error case if PDF could not be loaded
            }
        } else {
            // Handle error case if bookPdf is null
        }
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        binding.pageNumberText.text = "Page ${page + 1} of $pageCount"
        val sharedPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putInt("$PREF_PAGE_KEY-$bookPdf", page)
        editor.apply()
    }
}
