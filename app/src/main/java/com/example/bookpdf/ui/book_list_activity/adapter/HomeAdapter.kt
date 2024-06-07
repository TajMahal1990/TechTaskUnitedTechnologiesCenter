package com.example.bookpdf.ui.book_list_activity.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookpdf.data.model.BooksModel
import com.example.bookpdf.data.model.HomeModel
import com.example.bookpdf.databinding.ItemBodBinding
import com.example.bookpdf.databinding.ItemHomeBinding
import com.example.bookpdf.ui.categiry_activity.CategoryActivity
import com.example.bookpdf.ui.details_activity.DetailsActivity
import com.example.bookpdf.utils.loadOnline

const val LAYOUT_HOME = 0
const val LAYOUT_BOD = 1

class HomeAdapter(val list: ArrayList<HomeModel>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class HomeItemViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        val mViewPool = RecyclerView.RecycledViewPool()
        fun bind(model: HomeModel, context: Context) {
            binding.apply {
                model.apply {
                    mCategoryTitle.text = catTitle
                    mSeeAllBtn.setOnClickListener {

                    }
                    if (booksList != null) {
                        mChildRvBooks.setupChildRv(booksList, context)
                    }
                }
            }
        }
        private fun RecyclerView.setupChildRv(list: ArrayList<BooksModel>, context: Context) {
            val adapter = HomeChildAdapter(list, context)
            this.adapter = adapter
            setRecycledViewPool(mViewPool)
        }
    }

    class BODItemViewHolder(val binding: ItemBodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeModel, context: Context) {
            binding.apply {
                model.bod?.apply {
                    imageView.loadOnline(image.toString())
                    mReadBookBtn.setOnClickListener {

                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = list[position]
        return when (model.LAYOUT_TYPE) {
            LAYOUT_HOME -> LAYOUT_HOME
            else -> LAYOUT_BOD
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LAYOUT_HOME -> {
                HomeItemViewHolder(
                    ItemHomeBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                BODItemViewHolder(
                    ItemBodBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        when (model.LAYOUT_TYPE) {
            LAYOUT_HOME -> {
                (holder as HomeItemViewHolder).bind(model, context)
            }

            else -> {
                (holder as BODItemViewHolder).bind(model, context)
            }
        }
    }
}