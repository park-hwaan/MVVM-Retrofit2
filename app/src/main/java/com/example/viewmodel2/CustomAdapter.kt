package com.example.viewmodel2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodel2.databinding.PostItemBinding
import com.example.viewmodel2.mdoel.Post

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.MyView>(), ItemTouchHelperCallBack.ItemTouchHelperListener {

    private var postList = mutableListOf<Post>()

    fun setList(list: MutableList<Post>){
        postList=list
        notifyItemInserted(postList.size-1)
    }

    inner class MyView(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos: Int){
            binding.title.text = postList[pos].title
            binding.body.text = postList[pos].body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyView {
        val view = PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.MyView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onItemMove(from: Int, to: Int): Boolean {
        val item = postList[from]
        postList.removeAt(from)
        postList.add(to , item)
        notifyItemMoved(from , to)
        return true
    }

    override fun onItemSwipe(position: Int) {
        postList.removeAt(position)
        notifyItemRemoved(position)
    }
}