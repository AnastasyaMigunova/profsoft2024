package com.example.lesson7.presentation.main_screen.viewPager

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson7.presentation.main_screen.extension.toPx

class ViewPagerItemDecoration(private var space: Int = 4.toPx()) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left = space
        outRect.right = space
    }
}