package com.developnetwork.meshlwahdk.ui.main.selectpharmacy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.RedemptionCenter
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class SelectOrdersAdapter(private val click: (pharmacyId: Int, pharmacyname: String) -> Unit) :
    ListAdapter<RedemptionCenter, SelectOrdersAdapter.ViewHolder>(RedemptionCenterItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.selectable_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(holder.absoluteAdapterPosition)
        if (!item.logo.isNullOrBlank())
            holder.logo.setImageURL(item.logo)

        holder.name.text = item.name

        holder.itemView.setOnClickListener {
            click(item.id, item.name)
        }
    }

    class RedemptionCenterItemDiffCallback : DiffUtil.ItemCallback<RedemptionCenter>() {
        override fun areItemsTheSame(
            oldItem: RedemptionCenter,
            newItem: RedemptionCenter
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RedemptionCenter,
            newItem: RedemptionCenter
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ShapeableImageView = view.findViewById(R.id.logo)
        val name: MaterialTextView = view.findViewById(R.id.title)
    }
}