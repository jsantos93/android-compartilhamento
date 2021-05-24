package com.example.compartilhamento

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.compartilhamento_card.view.*

class ImageAdapter(private var img_list: List<ImageCard>, private var listener: MainActivity) : RecyclerView.Adapter<ImageAdapter.CompartilhamentoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompartilhamentoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.compartilhamento_card, parent,
            false)

        return CompartilhamentoHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompartilhamentoHolder, position: Int) {
        val currentItem = img_list[position]

//        holder.imgUriView?.setImageDrawable(currentItem.img_uri)
        holder.imgUriView?.setImageURI(currentItem.img_uri)
        holder.imgTextView?.text = currentItem.img_title
    }

    override fun getItemCount() = img_list.size

    inner class CompartilhamentoHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener{

        val imgUriView: ImageView? =  itemView.img_view_card
        val imgTextView: TextView? = itemView.img_text_card

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onItemClick(position)

        }
    }


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}