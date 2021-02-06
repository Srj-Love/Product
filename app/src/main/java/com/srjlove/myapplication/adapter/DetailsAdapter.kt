package com.srjlove.myapplication.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srjlove.myapplication.R
import com.srjlove.myapplication.callback.ProductClickListener
import com.srjlove.myapplication.databinding.ItemSubDetailsRowBinding
import com.srjlove.myapplication.holder.BaseViewHolder
import com.srjlove.myapplication.model.DetailsModel
import com.srjlove.myapplication.model.ProductDetails

class DetailsAdapter(
    private val context: Context,
    private val data: ArrayList<DetailsModel>,
    private val mListener: ProductClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var adapterDataList: List<Any> = emptyList()

    companion object {
        private const val TYPE_CATEGORY = 0
        private const val TYPE_PRODUCT = 1
    }

    inner class CategoryVH(itemView: View) : BaseViewHolder<String>(itemView) {
        private val binding = ItemSubDetailsRowBinding.bind(itemView)

        override fun bind(item: String) {
            binding.tvSubItemDetails.text = item
            binding.tvSubItemDetails.setCompoundDrawables(null, null, null, null)
            binding.tvSubItemDetails.setTypeface(binding.tvSubItemDetails.typeface, Typeface.BOLD)
        }
    }

    inner class ProductVH(itemView: View) : BaseViewHolder<ProductDetails>(itemView) {
        private val binding = ItemSubDetailsRowBinding.bind(itemView)

        override fun bind(item: ProductDetails) {
            binding.tvSubItemDetails.text = item.productName
            binding.tvSubItemDetails.setOnClickListener {
                mListener.onCLickProduct(bindingAdapterPosition, item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_CATEGORY -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_sub_details_row, parent, false)
                CategoryVH(view)
            }
            TYPE_PRODUCT -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_sub_details_row, parent, false)
                ProductVH(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = data[position]
        when (holder) {
            is CategoryVH -> holder.bind(element.category as String)
            is ProductVH -> holder.bind(element.productDetails as ProductDetails)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = data[position].viewType
        return when (comparable) {
            0 -> TYPE_CATEGORY
            1 -> TYPE_PRODUCT
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    override fun getItemCount() = data.size


}