package com.projects.aldajo92.mercadolibreproducts.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.aldajo92.mercadolibreproducts.BR
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.FragmentDashboardBinding
import com.projects.aldajo92.mercadolibreproducts.models.ProductModel
import com.projects.aldajo92.mercadolibreproducts.ui.recyclerAdapter.RecyclerItem
import com.projects.aldajo92.mercadolibreproducts.ui.recyclerAdapter.RecyclerViewAdapter
import kotlin.random.Random

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    private val productAdapter by lazy {
        RecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20)),
            ProductModel(Random.nextInt(9999999).toString(), generateRandomText(20))
        )

        val itemList = productList.map {
            RecyclerItem(it, R.layout.item_product, BR.model)
        }

        binding.recyclerViewProducts.adapter = productAdapter
        binding.recyclerViewProducts.layoutManager =
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )

        productAdapter.updateData(itemList)
    }

    // TODO: Delete this function when the data comes from a repository
    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') + List(10) { ' '}
    private fun generateRandomText(targetStringLength: Int): String {
        return (1..targetStringLength)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

}