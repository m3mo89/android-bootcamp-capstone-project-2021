package com.wizeline.bootcamp.capstone.ui.booklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wizeline.bootcamp.capstone.databinding.ItemBookBinding
import com.wizeline.bootcamp.capstone.domain.BookDTO
import com.wizeline.bootcamp.capstone.utils.getCryptoName

typealias OnBookClicked = (String) -> Unit

class BookListAdapter(
    private val onBookClicked: OnBookClicked
) : ListAdapter<BookDTO, BookListAdapter.BookViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemBookBinding.inflate(inflater, parent, false) }
            .let { binding -> BookViewHolder(binding, onBookClicked) }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(
        private val binding: ItemBookBinding,
        private val onBookClicked: OnBookClicked,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookDTO) {
            binding.bookId.text = book.id.toString()
            binding.bookName.text = book.name.getCryptoName(binding.root.context)
            Glide.with(binding.root).load(book.spriteUrl).into(binding.bookSpriteUrl)

            binding.root.setOnClickListener()
            {
                onBookClicked.invoke(book.id)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BookDTO>() {
            override fun areItemsTheSame(oldItem: BookDTO, newItem: BookDTO): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BookDTO, newItem: BookDTO): Boolean =
                oldItem == newItem
        }
    }
}