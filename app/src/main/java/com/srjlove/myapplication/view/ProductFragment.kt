package com.srjlove.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.srjlove.myapplication.adapter.ProductAdapter
import com.srjlove.myapplication.databinding.ProductFragmentBinding
import com.srjlove.myapplication.model.ProductDetails

//private const val TAG = "ProductFragment"

class ProductFragment : Fragment() {

    companion object {
        fun newInstance() = ProductFragment()
    }

    private lateinit var viewModel: ProductViewModel

    private lateinit var productList: ArrayList<ProductDetails>
    private lateinit var mAdapter: ProductAdapter

    private var _binding: ProductFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        submitProduct()
    }

    private fun submitProduct() {
        productList = arrayListOf()

        with(binding) {
            mAdapter = ProductAdapter(requireContext(), productList)
            rcvProducts.setHasFixedSize(true)
            rcvProducts.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )

            rcvProducts.adapter = mAdapter

            btnAdd.setOnClickListener {
                if (etCat.text.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Category Cannot be Empty", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if (etProd.text.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Product Cannot be Empty", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                val product = ProductDetails(0, etProd.text.toString(), etCat.text.toString())
                productList.add(product)
                mAdapter.notifyDataSetChanged()
            }

            btnSubmit.setOnClickListener {
                if (productList.isEmpty()){
                    Toast.makeText(requireContext(), "Please add Some Products", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                viewModel.addProduct(requireContext(), productList)
                clearText()
            }

        }

    }

    private fun clearText() {
        binding.etProd.text?.clear()
        binding.etCat.text?.clear()
        productList.clear()
        mAdapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}