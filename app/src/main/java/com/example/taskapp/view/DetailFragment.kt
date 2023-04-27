package com.example.taskapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.taskapp.databinding.FragmentDetailBinding
import com.example.taskapp.db.UserDbViewModel
import com.example.taskapp.model.UserDetail
import com.example.taskapp.model.UserRoom
import com.example.taskapp.services.ApiInterface
import com.example.taskapp.services.ServiceBuilder
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {


    private val args: DetailFragmentArgs by navArgs<DetailFragmentArgs>()
    private lateinit var binding : FragmentDetailBinding
    lateinit var viewModel: UserDbViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val serviceBuilder = ServiceBuilder.buildServices(ApiInterface::class.java)
        val requestCall = serviceBuilder.getUser(args.username)
        requestCall.enqueue(object : Callback<UserDetail>{
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                if (response.isSuccessful){
                    binding.user = response.body()
                    saveUser(binding.user)
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                Toast.makeText(context, "Сервак упал", Toast.LENGTH_SHORT).show()
            }

        })



    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbs: ImageView?, url: String?) {
            Picasso.get().load(url).into(thumbs)
        }
    }

    private fun saveUser(user: UserDetail?) {
        viewModel.addUser(UserRoom(user!!.name, user.avatar_url, user.email))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(
            activity?.application!!
        )).get(UserDbViewModel::class.java)
        return binding.root
    }

}