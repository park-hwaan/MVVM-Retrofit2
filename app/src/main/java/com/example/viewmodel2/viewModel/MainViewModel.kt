package com.example.viewmodel2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodel2.api.GetPostRepository
import com.example.viewmodel2.api.RetrofitWork
import com.example.viewmodel2.mdoel.Post
import com.example.viewmodel2.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

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
        val post = repository.getPost1()
        _mutableWord1.value = post.title
    }

    fun getPostNumber(number: Int) = viewModelScope.launch {
        val post = repository.getPostNumber(number)
        _mutableWord2.value = post.title
    }


    fun getPostAll() = viewModelScope.launch {
        val postAll = repository.getPostAll()
        _mutableWordList.value = postAll
    }


}