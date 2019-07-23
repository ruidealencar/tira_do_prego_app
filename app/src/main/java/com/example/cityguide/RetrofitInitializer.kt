package com.example.cityguide

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    fun init() {
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://3.83.179.114:5100")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiRetrofitService(): APIRetrofitService {
        return retrofit.create(APIRetrofitService::class.java)
    }

    //A mesma função criada na linha 20 pode ser feita de duas maneiras através da técnica Single-Expression function, são elas:

    //Single-Expression function com retorno
    //fun apiRetrofitService(): APIRetrofitService = retrofit.create(APIRetrofitService::class.java)

    //Single-Expression function ou sem retorno, pq o retorno se torna opcional
    //fun apiRetrofitService() = retrofit.create(APIRetrofitService::class.java)

}