package com.example.kotlinroomretrofit.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinroomretrofit.Fragments.HomeFragmentDirections
import com.example.kotlinroomretrofit.databinding.ItemListBinding
import com.example.kotlinroomretrofit.model.User

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()


    inner class MyViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.loginName.text = currentItem.login
        holder.binding.type.text = currentItem.type
        Glide.with(holder.itemView.context).load(currentItem.avatar_url).circleCrop()
            .into(holder.binding.imageView);

        holder.binding.rowLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(user: List<User>) {

        this.userList = user
        notifyDataSetChanged()
    }
}