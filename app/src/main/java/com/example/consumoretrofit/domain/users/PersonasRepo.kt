package com.example.consumoretrofit.domain.users

import com.example.consumoretrofit.data.models.BasePersonas

interface PersonasRepo {
    suspend fun getLista(): BasePersonas
    suspend fun getPersona(id_persona:Int):BasePersonas
}