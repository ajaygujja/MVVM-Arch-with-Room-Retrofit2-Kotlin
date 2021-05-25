package com.example.kotlinroomretrofit.Fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinroomretrofit.Database.UserDatabase
import com.example.kotlinroomretrofit.Adapter.HomeAdapter
import com.example.kotlinroomretrofit.R
import com.example.kotlinroomretrofit.databinding.FragmentHomeBinding
import com.example.kotlinroomretrofit.repository.UserRepository
import com.example.kotlinroomretrofit.viewModel.LocalUserViewModel
import com.example.kotlinroomretrofit.viewModel.UserViewModel
import com.example.kotlinroomretrofit.viewModel.UserViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mLocalUserViewModel: LocalUserViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //recyclerView
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())

        val repository = UserRepository()
        val viewModelFactory = UserViewModelFactory(repository)


        mUserViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
        mLocalUserViewModel = ViewModelProvider(this).get(LocalUserViewModel::class.java)
        mUserViewModel.getUser()



        if (checkInternetConnection(requireContext())){
            UserDatabase.getDatabase(requireContext()).userDao().readAllData()
                .observe(viewLifecycleOwner, { ajay ->

                    if (ajay.isEmpty()) {
                        mUserViewModel.myResponse.observe(viewLifecycleOwner, { response ->
                            Log.i("Ajay", "Remote ")

                            if (response.isSuccessful) {
                                response.body().let {
                                    if (it != null) {
                                        adapter.setData(it)
                                        adapter.notifyDataSetChanged()
                                        mLocalUserViewModel.addData(response)
                                    }

                                    Toast.makeText(requireContext(), "From Remote", Toast.LENGTH_SHORT)
                                        .show()

                                }
                                Log.d("Response", response.body().toString())
                            } else {
                                Log.d("Response", response.errorBody().toString())

                            }
                        })
                    } else {
                        Toast.makeText(requireContext(), "From Database", Toast.LENGTH_SHORT).show()
                        Log.i("TAG", "ajay: " + ajay.size)
                        adapter.setData(ajay)
                        adapter.notifyDataSetChanged()
                    }

                })
        }else{
            UserDatabase.getDatabase(requireContext()).userDao().readAllData()
                .observe(viewLifecycleOwner, Observer { ajay ->
                    if (ajay.isEmpty()) {
                        try {
//                            findNavController().navigate(R.id.action_homeFragment_to_noResult)
                            startActivity(Intent(requireContext(),NoResult::class.java))
                            Toast.makeText(requireContext(), "Internet Connection Required", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Log.e("TAG", "onCreateView: ",e )
                        }

//                        adapter.setData(null)
                    } else {
                        adapter.setData(ajay)
                        adapter.notifyDataSetChanged()
                    }
                })
        }



        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createFragment)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun checkInternetConnection(context: Context): Boolean {
        // get Connectivity Manager object to check connection
        val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Check for network connections
        if (connec.getNetworkInfo(0)!!.state ==
                NetworkInfo.State.CONNECTED || connec.getNetworkInfo(0)!!.state ==
                NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1)!!.state ==
                NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1)!!.state == NetworkInfo.State.CONNECTED) {
//            Toast.makeText(context, " Connected ", Toast.LENGTH_LONG).show();
            return true
        } else if (connec.getNetworkInfo(0)!!.state ==
                NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1)!!.state ==
                NetworkInfo.State.DISCONNECTED) {
            Toast.makeText(
                    context,
                    "Check Internet Connection",
                    Toast.LENGTH_LONG
            ).show()
            return false
        }
        return false
    }


    private fun checkNetwork(): Boolean {
        val connManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return networkInfo!=null && networkInfo.isConnected
    }

    fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }


}