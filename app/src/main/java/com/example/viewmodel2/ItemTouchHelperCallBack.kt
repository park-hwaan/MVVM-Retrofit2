package com.example.viewmodel2

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallBack(listener : ItemTouchHelperListener) : ItemTouchHelper.Callback() {
    private var itemTouchHelperListener : ItemTouchHelperListener = listener

    // ItemTouchHelper에서 callback 호출 시 실행되는 함수들
    interface ItemTouchHelperListener {
        fun onItemMove(from : Int , to : Int) : Boolean // drag and drop
        fun onItemSwipe(position : Int) // swipe
    }

    // drag and drop과 swipe에 대한 flag를 생성하는 함수
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.END or ItemTouchHelper.START
        return makeMovementFlags(dragFlags , swipeFlags)
    }

    // Item이 이동될때 호출되는 Callback
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return itemTouchHelperListener.onItemMove(viewHolder.adapterPosition , target.adapterPosition)
    }

    // Item이 Swipe될때 호출되는 Callback
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemTouchHelperListener.onItemSwipe(viewHolder.adapterPosition)
    }

    // ItemTouchHelper의 LongClick 가능 여부를 설정
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    // ItemTouchHelper의 Swipe 가능 여부를 설정
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
}