package com.example.bookpdf.ui.categiry_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookpdf.R
import com.example.bookpdf.data.model.BooksModel
import com.example.bookpdf.databinding.ActivityCategoryBinding
import com.example.bookpdf.ui.categiry_activity.adapter.CategoryAdapter

class CategoryActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    private val list = ArrayList<BooksModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            val bookList = intent.getSerializableExtra("book_list") as? ArrayList<BooksModel>
            bookList?.let {
                list.addAll(it)
            }
            val adapter = CategoryAdapter(list, this@CategoryActivity)
            mRvCategory.adapter = adapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        with(window) {
            sharedElementReenterTransition = null
            sharedElementReturnTransition = null
        }
        binding.mRvCategory.transitionName = null
    }
}
