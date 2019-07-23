package com.example.cityguide

import retrofit2.Call
import retrofit2.http.GET

interface APIRetrofitService {
    @GET("/api/prestador")
    fun getPrestador() : Call<List<Prestador>>


}