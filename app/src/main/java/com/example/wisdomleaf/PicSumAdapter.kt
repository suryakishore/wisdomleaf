package com.example.wisdomleaf

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daytoday.searchreponse.PicSumPhotosResponse
import com.example.wisdomleaf.databinding.ItemPicsumPhotosBinding


/**
 * Adapter class for the pic sum items.
 */
public class PicSumAdapter(data: ArrayList<PicSumPhotosResponse>, selectItem: SelectItem) :
    RecyclerView.Adapter<PicSumAdapter.PicSumViewHolder>() {
    private lateinit var mSelectItem: SelectItem
    private var mPicSumData: ArrayList<PicSumPhotosResponse>

    lateinit var context: Context

    init {
        mPicSumData = data
        mSelectItem = selectItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicSumAdapter.PicSumViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemPicsumPhotosBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_picsum_photos, parent, false)
        return PicSumViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mPicSumData != null) mPicSumData.size else 0
    }

    override fun onBindViewHolder(holder: PicSumAdapter.PicSumViewHolder, position: Int) {
        val picSumPhotosResponse = mPicSumData.get(position)
        if (picSumPhotosResponse != null) {
            Glide.with(context)
                .load(picSumPhotosResponse.download_url)
                .into(holder.itemBinding.ivPicSumPhoto)
            holder.itemBinding.tvPhotoId.text =
                """${context.resources.getString(R.string.id)} ${picSumPhotosResponse.id}"""
            holder.itemBinding.tvAuthor.text =
                """${context.resources.getString(R.string.author)} ${picSumPhotosResponse.author}"""
            holder.itemBinding.cvPicSumItem.setOnClickListener {
                mSelectItem.onSelectItem(position)
            }
        }
    }

    /**
     * view holder class for picsum photos items
     */
    class PicSumViewHolder(itemBinding: ItemPicsumPhotosBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var itemBinding: ItemPicsumPhotosBinding

        init {
            this.itemBinding = itemBinding
        }
    }
}