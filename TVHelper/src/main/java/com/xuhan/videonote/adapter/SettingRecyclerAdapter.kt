package com.xuhan.videonote.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.xuhan.videonote.R

import java.util.ArrayList

/**
 * @author  xuhan on 18-3-3.
 */

internal class SettingRecyclerAdapter : RecyclerView.Adapter<SettingRecyclerAdapter.ViewHolder> {

    private var mContext: Context? = null
    private var mDataList: List<String> = ArrayList()
    var mItemClickListener: OnItemClickListener? = null

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.item_icon)
        var itemName: TextView = itemView.findViewById(R.id.item_text)

    }

    constructor(dataList: List<String>) {
        this.mDataList = dataList
    }

    fun setDataList(dataList: List<String>) {
        this.mDataList = dataList
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_setting, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = mDataList[position]
        if (position < 3) {
            holder.itemImage.visibility = View.VISIBLE
            Glide.with(mContext).load(R.drawable.icon_arrow_right_gray).into(holder.itemImage)
        } else {
            holder.itemImage.visibility = View.GONE
        }
        holder.itemView.setOnClickListener { mItemClickListener?.onItemClick(position) }
    }

    override fun getItemCount(): Int {
        return if (mDataList.isEmpty()) 0 else mDataList.size
    }

    interface OnItemClickListener {
        /**
         * item点击事件
         *
         * @param position
         */
        fun onItemClick(position: Int)
    }
}
