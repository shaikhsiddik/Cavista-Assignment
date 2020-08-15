package com.axxess.cavistaassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.axxess.cavistaassignment.R
import com.bumptech.glide.Glide

import com.axxess.cavistaassignment.`interface`.ItemCallback
import com.axxess.cavistaassignment.ext.getImageUrl

import com.axxess.cavistaassignment.model.AppBean
import com.axxess.cavistaassignment.model.Data
import com.axxess.cavistaassignment.model.ErrorPageModel
import com.axxess.cavistaassignment.model.PagerProgressModel
import java.util.*

class SearchAdapter(
    internal var mContext: Context,
    private var tracklist: ArrayList<AppBean>, private var itemCallback: ItemCallback<Data, View>
) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return tracklist.size;
    }

    private var ERROR = 3;
    private var PROGRESS_LOADER = 2;
    private var VIEW_LOADER = 1;
    private var layoutInflater: LayoutInflater? = null

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, type: Int): MyViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        var view: View? = null
        view = when (type) {
            PROGRESS_LOADER -> {
                layoutInflater!!.inflate(R.layout.row_progress, null, false);
            }
            VIEW_LOADER -> {
                layoutInflater!!.inflate(R.layout.row_search_items, null, false);
            }
            else -> {
                layoutInflater!!.inflate(R.layout.row_error, null, false);
            }
        }

        return MyViewHolder(view!!)
    }

    override fun onBindViewHolder(@NonNull myViewHolder: MyViewHolder, i: Int) {

        if (tracklist[i] is Data) {
            val data = tracklist[i] as Data;
            myViewHolder.trackName?.text = data.title;
            val imageUrl = data.getImageUrl()
            Glide.with(myViewHolder.imageView!!).load(imageUrl).placeholder(R.drawable.loader)
                .into(myViewHolder.imageView!!)
        }


    }

    override fun getItemViewType(position: Int): Int {
        return when {
            tracklist[position] is PagerProgressModel -> {
                PROGRESS_LOADER;
            }
            tracklist[position] is ErrorPageModel -> {
                ERROR;
            }
            else -> {
                VIEW_LOADER;

            }
        }
    }

    inner class MyViewHolder(@param:NonNull val mView: View) :
        RecyclerView.ViewHolder(mView) {
        var trackName: TextView? = null;
        var imageView: ImageView? = null;

        init {
            imageView = itemView.findViewById(R.id.image)
            trackName = itemView.findViewById(R.id.title)
            itemView.setOnClickListener {
                val index = adapterPosition;
                if (index != RecyclerView.NO_POSITION && tracklist[index] is Data) {
                    itemCallback.OnClick(tracklist[index] as Data, imageView!!);
                }
            }
        }


    }

}