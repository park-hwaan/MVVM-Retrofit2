package com.example.viewmodel2.repository

import com.example.viewmodel2.api.GetPostRepository
import com.example.viewmodel2.api.RetrofitWork
import com.example.viewmodel2.mdoel.Post

class Repository : GetPostRepository {
    private val retrofitInstance = RetrofitWork.getInstance().create(GetPostRepository::class.java)

    override suspend fun getPost1(): Post {
        return retrofitInstance.getPost1()
    }

    override suspend fun getPostNumber(number: Int): Post {
        return retrofitInstance.getPostNumber(number)
    }

    override suspend fun getPostAll(): List<Post> {
        return retrofitInstance.getPostAll()
    }
}