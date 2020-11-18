package com.sk.quosphereassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sk.quosphereassignment.R
import com.sk.quosphereassignment.models.ImageData
import kotlinx.android.synthetic.main.list_item_image.view.*


class ImageListAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    private var imageList = emptyList<ImageData>()

    /**
     * Set Updated list to adapter and notify Adapter
     *
     * @param list
     */
    fun setData(list: List<ImageData>) {
        imageList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImageView: ImageView = view.itemImageView

        init {
            itemImageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onClick(imageList[position], position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_image,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = imageList[position]
        holder.itemImageView.setImageResource(data.imageID)
    }


    override fun getItemCount(): Int = imageList.size

    /**
     * RecyclerView Click Listeners
     *
     */
    interface OnClickListener {
        fun onClick(data: ImageData, position: Int)
    }
}