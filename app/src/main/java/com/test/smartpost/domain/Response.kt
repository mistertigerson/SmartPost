package com.test.smartpost.domain

sealed class Response<out T>(
    val data: T? =null,
    val message: String?=null
){
    class Loading<T>(data: T? = null): Response<T>(data = data)
    class Success<T>(data: T): Response<T>(data = data)
    class Error<T>(message: String,data: T? = null): Response<T>(data = data, message = message)
}
