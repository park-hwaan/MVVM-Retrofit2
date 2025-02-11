package com.example.viewmodel2

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.viewmodel2.databinding.ActivityMainBinding
import com.example.viewmodel2.mdoel.Post
import com.example.viewmodel2.viewModel.MainViewModel
import com.example.viewmodel2.workManager.WorkManagerA
import com.example.viewmodel2.workManager.WorkManagerB

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dummyPosts = mutableListOf(
            Post(1, 1, "첫 번째 게시글", "이것은 첫 번째 게시글의 내용입니다."),
            Post(1, 2, "두 번째 게시글", "이것은 두 번째 게시글의 내용입니다."),
            Post(2, 3, "세 번째 게시글", "이것은 세 번째 게시글의 내용입니다."),
            Post(2, 4, "네 번째 게시글", "이것은 네 번째 게시글의 내용입니다."),
            Post(3, 5, "다섯 번째 게시글", "이것은 다섯 번째 게시글의 내용입니다.")
        )

        initRecyclerView(dummyPosts)


//        val myData = workDataOf(
//            "a" to 10,
//            "b" to 20
//        )

//        //WorkManager builder,       데이터를 담아서 실행 클래스에 넘겨주고 싶으면 setInputData를 사용하면 됨
//        val workManagerA = OneTimeWorkRequestBuilder<WorkManagerA>().setInputData(myData).build()
//        val workManagerB = OneTimeWorkRequestBuilder<WorkManagerB>().build()
//        WorkManager.getInstance(this).enqueue(workManagerA)
//        WorkManager.getInstance(this).enqueue(workManagerB)
//
//        // workManager 실행 완료후 데이터를 받아오는 방법
//        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workManagerA.id).observe(this,
//            Observer {
//                if (it != null && it.state.isFinished){
//                    val result = it.outputData.getInt("result", 10000)
//                    Log.d("MainActivity", result.toString())
//                }
//            })


//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        viewModel.getPost1()
//        viewModel.getPostNumber(2)
//        viewModel.getPostAll()
//
//        // 옵저브에서 엑티비티의 경우는 this를 쓰지만 fragment의 경우는 lifeCycleOwner를 사용함
//        viewModel.liveWord1.observe(this, Observer {
//            binding.area1.text = it.toString()
//        })
//
//        viewModel.liveWord2.observe(this, Observer {
//            binding.area2.text = it.toString()
//        })

        // retrofit2으로 불러온 데이터들을 리싸이클러뷰에 연결
        // observe는 실시간으로 이벤트를 감지해서 view에 반영
//        viewModel.liveWordList.observe(this, Observer {
//            customAdapter.setList(it)
//        })

//        binding.area1.setOnClickListener {
//            val intent = Intent(this,MainActivity2::class.java)
//            var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
//                this,
//                Pair.create(binding.area1,"text1"),
//                Pair.create(binding.area2, "text2")
//            )
//            startActivity(intent, options.toBundle())
//        }
    }

    private fun initRecyclerView(post: MutableList<Post>) {
        val customAdapter = CustomAdapter()
        customAdapter.setList(post)
        binding.recycle.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = customAdapter
        }
        val itemTouchHelperCallBack = ItemTouchHelperCallBack(customAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)

        itemTouchHelper.attachToRecyclerView(binding.recycle)
    }
}