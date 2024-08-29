package com.example.viewmodel2.workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.Thread.sleep

class WorkManagerA(context : Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {

        // 여기서 defaultValue는 혹시 데이터가 넘어오지 않았을때를 대비한 값
        val a = inputData.getInt("a",1000)
        val b = inputData.getInt("b",2000)

        Log.d("inputData","$a , $b")

        val output  = workDataOf("result" to 10)

        // 실행 엑티비티 보낼 데이터를 return 값에 담기
        return Result.success(output)
    }


}