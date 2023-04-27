package com.example.taskapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.UserCardBinding
import com.example.taskapp.model.User
import com.squareup.picasso.Picasso

class UsersAdapter(val itemClick : (User) -> Unit) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var users = mutableListOf<User>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUserList(users : List<User>){
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    internal interface OnUserClickListener {
        fun onUserClick(user: User?, position: Int)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(UserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.apply {
            bind(users[position],itemClick)
        }


    }

    override fun getItemCount(): Int = users.size

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbs: ImageView, url: String) {
            Picasso.get().load(url).into(thumbs)
        }
    }

    class UsersViewHolder(val binding : UserCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user : User, itemClick : (User) -> Unit){
            binding.u = user
            binding.root.setOnClickListener {
                itemClick(user)
            }
            
        }

    }

}


