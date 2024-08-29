package com.example.viewmodel2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodel2.api.GetPostRepository
import com.example.viewmodel2.api.RetrofitWork
import com.example.viewmodel2.mdoel.Post
import com.example.viewmodel2.repository.Repository
import kotlinx.coroutines.Dispatchers
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

    fun getPost1() = viewModelScope.launch(Dispatchers.IO){
        val post = repository.getPost1()
        _mutableWord1.postValue(post.title)
    }

    fun getPostNumber(number: Int) = viewModelScope.launch(Dispatchers.IO) {
        val post = repository.getPostNumber(number)
        _mutableWord2.postValue(post.title)
    }


    //백그라운드 쓰레드에서 livedata의 값을 변경하려면 .value 대신 postvalue를 사용해야함
    // postValue -> 값을 변경하고 이를 메인스레드에서 안전하게 처리하도록 함
    fun getPostAll() = viewModelScope.launch(Dispatchers.IO) {
        val postAll = repository.getPostAll()
        _mutableWordList.postValue(postAll)
    }


}