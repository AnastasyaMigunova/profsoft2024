package com.example.lesson7.presentation.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson7.R
import com.example.lesson7.databinding.ActivityMainBinding
import com.example.lesson7.presentation.main_screen.extension.toPx
import com.example.lesson7.presentation.main_screen.models.BigItem
import com.example.lesson7.presentation.main_screen.models.ListItems
import com.example.lesson7.presentation.main_screen.models.SmallItem
import com.example.lesson7.presentation.main_screen.recyclerView.ItemClickListener
import com.example.lesson7.presentation.main_screen.recyclerView.RecyclerViewAdapter
import com.example.lesson7.presentation.main_screen.viewPager.ViewPagerAdapter
import com.example.lesson7.presentation.main_screen.viewPager.ViewPagerItemDecoration
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val itemsAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        setViewPager()

        binding.buttonAddItem.setOnClickListener {
            itemsAdapter.addItem()
        }

    }

    private fun setRecyclerView() {
        val listItemsSmall = listOf(
            ListItems.SmallItemInfo(SmallItem(getString(R.string.item_1))),
            ListItems.SmallItemInfo(SmallItem(getString(R.string.item_2))),
            ListItems.SmallItemInfo(SmallItem(getString(R.string.item_3)))
        )

        val listItemsBig = listOf(
            ListItems.BigItemInfo(BigItem(getString(R.string.item_1))),
            ListItems.BigItemInfo(BigItem(getString(R.string.item_2))),
            ListItems.BigItemInfo(BigItem(getString(R.string.item_3)))
        )

        binding.recyclerView.adapter = itemsAdapter.apply {
            itemClickListener = object : ItemClickListener {
                override fun onFirstTypeItemClick(smallItem: SmallItem) {
                    val position =
                        items.indexOfFirst { it is ListItems.SmallItemInfo && it.smallInfo == smallItem }
                    if (position != -1) {
                        removeItem(position)
                    }
                }

                override fun onSecondTypeItemClick(bigItem: BigItem) {
                    val position =
                        items.indexOfFirst { it is ListItems.BigItemInfo && it.bigInfo == bigItem }
                    if (position != -1) {
                        removeItem(position)
                    }
                }
            }
        }

        itemsAdapter.setList(
            listOf(
                *listItemsSmall.toTypedArray(),
                *listItemsBig.toTypedArray()
            )
        )
    }

    private fun setViewPager() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val viewPagerAdapter = ViewPagerAdapter()
        with(viewPager) {
            adapter = viewPagerAdapter
            viewPagerAdapter.setList(
                listOf(
                    "item 1",
                    "item 2",
                    "item 3"
                )
            )

            binding.springDotsIndicator.setViewPager2(binding.viewPager)
            addItemDecoration(ViewPagerItemDecoration(64.toPx()))

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Tab 1"
                    1 -> tab.text = "Tab 2"
                    2 -> tab.text = "Tab 3"
                }
            }.attach()
        }
    }
}