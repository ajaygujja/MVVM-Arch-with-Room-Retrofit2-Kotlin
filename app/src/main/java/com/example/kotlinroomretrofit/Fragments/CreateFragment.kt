package com.example.kotlinroomretrofit.Fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinroomretrofit.R
import com.example.kotlinroomretrofit.databinding.FragmentCreateBinding
import com.example.kotlinroomretrofit.model.User
import com.example.kotlinroomretrofit.viewModel.LocalUserViewModel

class CreateFragment : Fragment() {

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var mLocalUserViewModel: LocalUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentCreateBinding.inflate(inflater, container, false)

        mLocalUserViewModel = ViewModelProvider(this).get(LocalUserViewModel::class.java)

        binding.createButton.setOnClickListener {
            addDataToDatabase()
        }

        return binding.root
    }

    private fun addDataToDatabase() {
        val name = binding.CreateName.text.toString()
        val type = binding.CreateType.text.toString()
        val avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4"

        if (inputCheck(name, type)) {
            val user = User(0, name, type, avatarUrl)

            mLocalUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Added $name", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createFragment_to_homeFragment)

        } else {
            Toast.makeText(requireContext(), "Please Add all the Data", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(Name: String, Type: String): Boolean {
        return !(TextUtils.isEmpty(Name) && TextUtils.isEmpty(Type))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}