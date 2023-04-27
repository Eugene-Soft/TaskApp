package com.example.taskapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taskapp.adapters.UsersAdapter
import com.example.taskapp.databinding.FragmentUsersBinding
import com.example.taskapp.services.ServiceBuilder
import com.example.taskapp.repository.UsersRepository
import com.example.taskapp.services.UsersViewModel
import com.example.taskapp.viewmodel.ViewModelFactory

class UsersFragment : Fragment() {


    private lateinit var binding : FragmentUsersBinding
    lateinit var viewModel: UsersViewModel
    var adapter: UsersAdapter? = null
    val serviceBuilder = ServiceBuilder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelFactory(UsersRepository(serviceBuilder))).get(
            UsersViewModel::class.java)

        binding.refreshLayout.setOnRefreshListener {
            viewModel.userList.observe(viewLifecycleOwner, Observer {
                adapter?.setUserList(it)
            })
            viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            })
            viewModel.getAllUsers()
            binding.refreshLayout.isRefreshing = false
        }

        binding.recyclerview.adapter = adapter
        viewModel.userList.observe(viewLifecycleOwner, Observer {
            adapter?.setUserList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
        })
        viewModel.getAllUsers()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(){
            val action = UsersFragmentDirections.actionUsersFragmentToDetailFragment(it.login);
            findNavController().navigate(action)
        }
        return binding.root
    }

}