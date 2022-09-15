package com.alif.crudbasic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alif.crudbasic.R
import com.alif.crudbasic.databinding.ItemListBinding
import com.alif.crudbasic.db.Subscribers

class MyRecycleViewAdapter(private val subscriberList: List<Subscribers>): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemListBinding>(layoutInflater, R.layout.item_list, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position])
    }

    override fun getItemCount(): Int = subscriberList.size

}

class MyViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(subscribers: Subscribers) {
        binding.nameTextView.text = subscribers.name
        binding.emailTextView.text = subscribers.email
    }
}