package com.example.consumoretrofit.data.rest
import com.example.consumoretrofit.core.Appconstants
import com.example.consumoretrofit.data.models.BasePersonas
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("users?page=2")
    suspend fun getLista(): BasePersonas

    @GET("users/{id_persona}")
    suspend fun getPersona(@Path("id_persona")id_persona:Int):BasePersonas
}
object RetrofitClient{
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(Appconstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}