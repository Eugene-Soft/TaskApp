package com.example.taskapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.model.UserRoom
import com.squareup.picasso.Picasso

class UserHistoryAdapter(
    val context : Context,
) : RecyclerView.Adapter<UserHistoryAdapter.UserHistoryVH>() {

    private val allUsers = ArrayList<UserRoom>()

    class UserHistoryVH(view : View) : RecyclerView.ViewHolder(view) {
        val userAvatar = view.findViewById<ImageView>(R.id.user_avatar_history)
        val userName = view.findViewById<TextView>(R.id.user_login_history)
        val userEmail = view.findViewById<TextView>(R.id.user_id_history)

        fun bindView(user : UserRoom){
            userEmail.text = user.email
            userName.text = user.name
            Picasso.get().load(user.avatar).into(userAvatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHistoryVH =
        UserHistoryVH(LayoutInflater.from(context).inflate(R.layout.user_card_history, parent, false))

    override fun getItemCount(): Int = allUsers.size

    override fun onBindViewHolder(holder: UserHistoryVH, position: Int) {
        holder.bindView(allUsers.get(position))
    }

    fun updateList(newList : List<UserRoom>){
        allUsers.clear()
        allUsers.addAll(newList)
        notifyDataSetChanged()
    }

}