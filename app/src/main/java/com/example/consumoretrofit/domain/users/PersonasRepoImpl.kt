package com.example.consumoretrofit.domain.users

import com.example.consumoretrofit.data.datasource.PersonasDataSource
import com.example.consumoretrofit.data.models.BasePersonas


class PersonasRepoImpl(private  val datasource: PersonasDataSource):PersonasRepo{
    override suspend fun getLista(): BasePersonas = datasource.getLista()
    override suspend fun getPersona(id_persona:Int): BasePersonas  = datasource.getPersona(id_persona)
}