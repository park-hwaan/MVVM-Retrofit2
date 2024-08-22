package com.example.viewmodel2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodel2.api.MyApi
import com.example.viewmodel2.api.RetrofitWork
import com.example.viewmodel2.mdoel.Post
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainViewModel : ViewModel() {

    private val retrofitInstance = RetrofitWork.getInstance().create(MyApi::class.java)

    private var _mutableWord1 = MutableLiveData<String>()
    val liveWord1 : LiveData<String>
        get() = _mutableWord1

    private var _mutableWord2 = MutableLiveData<String>()
    val liveWord2 : LiveData<String>
        get() = _mutableWord2

    private var _mutableWordList = MutableLiveData<List<Post>>()
    val liveWordList : LiveData<List<Post>>
        get() = _mutableWordList
    fun getPost1() = viewModelScope.launch{
        val post = retrofitInstance.getPost1()
        _mutableWord1.value = post.title
    }

    fun getPostNumber(number: Int) = viewModelScope.launch {
        val post = retrofitInstance.getPostNumber(number)
        _mutableWord2.value = post.title
    }


    fun getPostAll() = viewModelScope.launch {
        val postAll = retrofitInstance.getPostAll()
        _mutableWordList.value = postAll
    }


}