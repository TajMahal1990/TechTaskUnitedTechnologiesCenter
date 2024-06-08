package com.example.bookpdf.ui.book_list_activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookpdf.data.model.BooksModel
import com.example.bookpdf.databinding.ItemBookBinding
import com.example.bookpdf.ui.pdf_activity.PdfActivity
import com.example.bookpdf.utils.loadOnline

class HomeChildAdapter(val list: ArrayList<BooksModel>, val context: Context) :
    RecyclerView.Adapter<HomeChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BooksModel, context: Context) {
            binding.apply {
                model.apply {
                    imageView.loadOnline(image)
                    cardView.setOnClickListener {
                        val intent = Intent(context, PdfActivity::class.java).apply {
                            putExtra("book_pdf", bookPDF) // Change to the asset file name
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(ItemBookBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model, context)
    }
}
