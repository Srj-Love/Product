package com.srjlove.myapplication.adapter

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.srjlove.myapplication.R
import com.srjlove.myapplication.databinding.ItemSubDetailsRowBinding
import com.srjlove.myapplication.model.ProductDetails

class ProductAdapter(
    private val context: Context,
    private val mList: ArrayList<ProductDetails>
) : RecyclerView.Adapter<ProductAdapter.ProductVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        return ProductVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_sub_details_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val model = mList[position]
        holder.binding.tvSubItemDetails.text = model.productName
        holder.binding.tvSubItemDetails.setCompoundDrawables(null, null, null, null)

    }



    class ProductVH(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSubDetailsRowBinding.bind(view)
    }

}