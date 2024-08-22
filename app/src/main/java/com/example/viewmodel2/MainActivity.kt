package com.example.viewmodel2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewmodel2.databinding.ActivityMainBinding
import com.example.viewmodel2.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getPost1()
        viewModel.getPostNumber(2)
        viewModel.getPostAll()

        viewModel.liveWord1.observe(this, Observer {
            binding.area1.text = it.toString()
        })

        viewModel.liveWord2.observe(this, Observer {
            binding.area2.text = it.toString()
        })

        // retrofit2으로 불러온 데이터들을 리싸이클러뷰에 연결
        viewModel.liveWordList.observe(this, Observer {
            val customAdapter = CustomAdapter()
            binding.recycle.adapter = customAdapter
            binding.recycle.layoutManager = LinearLayoutManager(this)
            customAdapter.setList(it)

        })
    }
}