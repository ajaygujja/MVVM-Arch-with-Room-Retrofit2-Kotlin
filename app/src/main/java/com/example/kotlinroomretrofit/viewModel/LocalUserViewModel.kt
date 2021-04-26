package com.example.kotlinroomretrofit.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinroomretrofit.Database.UserDatabase
import com.example.kotlinroomretrofit.model.User
import com.example.kotlinroomretrofit.repository.UserLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LocalUserViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData: LiveData<List<User>>
    private val repository: UserLocalRepository

    init {
        val userDao = UserDatabase.getDatabase(
            application
        ).userDao()
        repository = UserLocalRepository(userDao)
        readAllData = repository.readAllData

    }

    fun addData(response: Response<List<User>>) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("DataBase Response", "data: " + response.body())
            val userdata = response.body()
            if (userdata != null) {
                for (i in 0 until (userdata.count())) {
                    addUser(userdata[i])
                    Log.i("DATA", "data: " + userdata[i])
                }
            }


        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


}