package com.example.viewmodel2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.viewmodel2.databinding.ActivityMainBinding
import com.example.viewmodel2.viewModel.MainViewModel
import com.example.viewmodel2.workManager.WorkManagerA
import com.example.viewmodel2.workManager.WorkManagerB

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val customAdapter = CustomAdapter()
        binding.recycle.adapter = customAdapter
        binding.recycle.layoutManager = LinearLayoutManager(this)

        val myData = workDataOf(
            "a" to 10,
            "b" to 20
        )

        //WorkManager builder,       데이터를 담아서 실행 클래스에 넘겨주고 싶으면 setInputData를 사용하면 됨
        val workManagerA = OneTimeWorkRequestBuilder<WorkManagerA>().setInputData(myData).build()
        val workManagerB = OneTimeWorkRequestBuilder<WorkManagerB>().build()
        WorkManager.getInstance(this).enqueue(workManagerA)
        WorkManager.getInstance(this).enqueue(workManagerB)

        // workManager 실행 완료후 데이터를 받아오는 방법
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workManagerA.id).observe(this,
            Observer {
                if (it != null && it.state.isFinished){
                    val result = it.outputData.getInt("result", 10000)
                    Log.d("MainActivity", result.toString())
                }
            })


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getPost1()
        viewModel.getPostNumber(2)
        viewModel.getPostAll()

        // 옵저브에서 엑티비티의 경우는 this를 쓰지만 fragment의 경우는 lifeCycleOwner를 사용함
        viewModel.liveWord1.observe(this, Observer {
            binding.area1.text = it.toString()
        })

        viewModel.liveWord2.observe(this, Observer {
            binding.area2.text = it.toString()
        })

        // retrofit2으로 불러온 데이터들을 리싸이클러뷰에 연결
        // observe는 실시간으로 이벤트를 감지해서 view에 반영
        viewModel.liveWordList.observe(this, Observer {
            customAdapter.setList(it)
        })
    }
}