package com.example.taskapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.adapters.UserHistoryAdapter
import com.example.taskapp.databinding.FragmentDetailBinding
import com.example.taskapp.databinding.FragmentHistoryBinding
import com.example.taskapp.db.UserDbViewModel

class HistoryFragment : Fragment() {

    lateinit var binding : FragmentHistoryBinding
    lateinit var viewModel: UserDbViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter = UserHistoryAdapter(requireContext())
        binding.recyclerviewHistory.adapter = userAdapter
        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            it?.let {
                userAdapter.updateList(it)
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(
            activity?.application!!
        )).get(UserDbViewModel::class.java)
        return binding.root
    }

}