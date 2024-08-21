package com.example.viewmodel2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel2.databinding.ActivityMainBinding
import com.example.viewmodel2.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getPost1()
        viewModel.getPostNumber(2)

        viewModel.liveWord1.observe(this, Observer {
            binding.area1.text = it.toString()
        })

        viewModel.liveWord2.observe(this, Observer {
            binding.area2.text = it.toString()
        })
    }
}