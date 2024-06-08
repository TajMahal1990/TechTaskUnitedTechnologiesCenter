package com.example.bookpdf.ui.categiry_activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookpdf.data.model.BooksModel
import com.example.bookpdf.databinding.ItemCategoryBinding
import com.example.bookpdf.ui.details_activity.DetailsActivity
import com.example.bookpdf.utils.loadOnline


class CategoryAdapter(val list: ArrayList<BooksModel>, val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BooksModel, context: Context) {
            binding.apply {
                mBookTitle.text = model.title
                mBookImage.loadOnline(model.image)
                mBookDesc.text = model.description
                mAuthorName.text = model.author
                binding.root.setOnClickListener{
                    Intent().apply {
                        putExtra("book_model",model)
                        setClass(context,DetailsActivity::class.java)
                        context.startActivity(this)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(
            model = list[position], context = context
        )
    }
}