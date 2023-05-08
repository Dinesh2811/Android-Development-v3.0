package com.dinesh.android.rv.kotlin.adv.swipe_drag_gesture

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dinesh.android.R
import com.google.android.material.checkbox.MaterialCheckBox


@SuppressLint("ClickableViewAccessibility")
class RvAdapter(): RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    var rvModelList: List<RvModel> = emptyList()
    var listener: RvMain? = null

    constructor(rvModelList: List<RvModel>) : this() {
        this.rvModelList = rvModelList
    }

    constructor(rvModelList: List<RvModel>, listener: RvMain) : this() {
        this.rvModelList = rvModelList
        this.listener = listener
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.iv_profilePic)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_name)
        val tvPosition: TextView = itemView.findViewById(R.id.tv_position)
        val checkbox: MaterialCheckBox = itemView.findViewById(R.id.checkbox)
        val dragButton: ImageButton = itemView.findViewById(R.id.iv_drag)

        init {
            dragButton.setOnTouchListener { _, _ ->
                listener?.startDragging(this)
                return@setOnTouchListener true
            }

            itemView.setOnClickListener { listener?.onItemClick(it, bindingAdapterPosition) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_basic_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.ivIcon.setImageResource(rvModelList[position].profilePic)
        holder.tvTitle.text = rvModelList[position].name
        holder.tvPosition.text = position.toString()

        Glide.with(holder.itemView.context)
            .load("https://loremflickr.com/20$position/20$position/dog")
            .placeholder(rvModelList[position].profilePic)
            .error(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(holder.ivIcon)

        holder.checkbox.visibility = View.GONE
        holder.dragButton.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return rvModelList.size
    }
}
