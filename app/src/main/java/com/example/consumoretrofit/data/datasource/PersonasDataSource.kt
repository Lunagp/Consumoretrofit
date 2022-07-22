package com.example.consumoretrofit.data.datasource

import com.example.consumoretrofit.data.models.BasePersonas
import com.example.consumoretrofit.data.rest.WebService

class PersonasDataSource(private val webService: WebService){
    suspend fun getLista(): BasePersonas = webService.getLista()
    suspend fun getPersona(id_persona:Int): BasePersonas = webService.getPersona(id_persona)
}