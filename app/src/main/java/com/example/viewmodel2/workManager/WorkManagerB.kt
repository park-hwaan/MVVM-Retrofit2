package com.example.viewmodel2.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WorkManagerB(context: Context, params : WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        test1()
        test2()

        return Result.success()
    }

    suspend fun test1(){
        for (i in 0..3){
            Log.d("WorkManagerTest1",i.toString())
        }
    }

    suspend fun test2(){
        for (i in 0..3){
            Log.d("WorkManagerTest2",i.toString())
        }
    }

}