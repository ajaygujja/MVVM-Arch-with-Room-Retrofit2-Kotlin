package com.example.kotlinroomretrofit.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kotlinroomretrofit.R
import com.example.kotlinroomretrofit.databinding.FragmentDetailBinding
import com.example.kotlinroomretrofit.viewModel.LocalUserViewModel


class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var mLocalUserViewModel: LocalUserViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        mLocalUserViewModel = ViewModelProvider(this).get(LocalUserViewModel::class.java)


        binding.profileName.text = args.currentUser.login
        Glide.with(this).load(args.currentUser.avatar_url).into(binding.avatarImg)

        binding.deleteBtn.setOnClickListener {
            deleteUser()
        }
        return binding.root
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_->
            mLocalUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully removed: ${args.currentUser.login}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete ${args.currentUser.login}?")
        builder.setMessage("Are you sure you want to delete")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}