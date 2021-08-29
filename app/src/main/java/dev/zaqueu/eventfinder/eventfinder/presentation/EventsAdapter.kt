package dev.zaqueu.eventfinder.eventfinder.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dev.zaqueu.eventfinder.R
import dev.zaqueu.eventfinder.common.domain.model.Event
import dev.zaqueu.eventfinder.common.utils.handleSingleClick
import dev.zaqueu.eventfinder.databinding.ItemEmphasisBinding

class EventsAdapter(
    private val glide: RequestManager
) : ListAdapter<Event, EventsAdapter.ViewHolder>(DIFF_CALLBACK) {
    private var onEventClickListener: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemEmphasisBinding.inflate(inflate, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnEventClickListener(listener: (Event) -> Unit) {
        onEventClickListener = listener
    }

    inner class ViewHolder(
        private val binding: ItemEmphasisBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.apply {
                tvTitle.text = event.title
                tvDescription.text = event.description
                glide
                    .load(event.image)
                    .placeholder(R.drawable.placeholder)
                    .into(ivCover)
            }
            binding.root.setOnClickListener {
                it.handleSingleClick {
                    onEventClickListener?.invoke(event)
                }
            }
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
}
