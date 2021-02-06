package com.srjlove.myapplication.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.srjlove.myapplication.R
import com.srjlove.myapplication.adapter.DetailsAdapter
import com.srjlove.myapplication.callback.ProductClickListener
import com.srjlove.myapplication.databinding.DetailsFragmentBinding
import com.srjlove.myapplication.model.DetailsModel
import com.srjlove.myapplication.model.ProductDetails

private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment(), ProductClickListener {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var mutableList: ArrayList<DetailsModel>

    private lateinit var viewModel: ProductViewModel
    private lateinit var mAdapter: DetailsAdapter

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        init()
    }

    private fun init() {
        mutableList = arrayListOf()
        val linkedHashMap: LinkedHashMap<String, List<ProductDetails>> = LinkedHashMap()
        mAdapter = DetailsAdapter(requireContext(), mutableList, this)
        with(binding) {
            rcvDetails.setHasFixedSize(true)
            rcvDetails.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rcvDetails.adapter = mAdapter
        }

        viewModel.getAllCategory(requireContext()).observe(this, {
            it.map { cat ->
                viewModel.getProducts(requireContext(), cat).observe(this, { pm ->
                    linkedHashMap[cat] = pm

                    linkedHashMap.forEach { map ->
                        val element1 = DetailsModel(0, map.key, null)
                        if (mutableList.contains(element1).not())
                            mutableList.add(element1)
                        map.value.forEach { mapValue ->
                            val element = DetailsModel(1, null, mapValue)
                            if (mutableList.contains(element).not())
                                mutableList.add(element)
                        }

                        Log.i(TAG, "init: Hashmap ${map.key} ${map.value}")
                    }
                    mAdapter.notifyDataSetChanged()

                })

            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCLickProduct(position: Int, productDetails: ProductDetails) {

        val messageBoxView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_product_edit, null)
        val messageBoxBuilder = AlertDialog.Builder(requireContext()).setView(messageBoxView)


        val etEditProd = messageBoxView.findViewById(R.id.etEditProd) as TextInputEditText
        val yesBtn = messageBoxView.findViewById(R.id.btnAddProduct) as Button

        etEditProd.setText(productDetails.productName)

        val messageBoxInstance = messageBoxBuilder.show()
        messageBoxInstance.setCancelable(true)

        yesBtn.setOnClickListener() {
            val details = productDetails
            details.productName = etEditProd.text.toString()
            viewModel.updateProduct(requireContext(),details)
            messageBoxInstance.dismiss()
        }
    }

}