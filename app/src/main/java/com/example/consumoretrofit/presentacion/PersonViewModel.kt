package com.example.consumoretrofit.presentacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.consumoretrofit.core.Result
import com.example.consumoretrofit.data.datasource.PersonasDataSource
import com.example.consumoretrofit.data.models.BasePersonas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PersonViewModel(private val repoImpl: PersonasDataSource):ViewModel(){

    fun fetchPerson(id_persona:Int): StateFlow<Result<BasePersonas>> = flow {
        kotlin.runCatching {
            repoImpl.getPersona(id_persona)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Loading()
    )

}
class PersonViewModelFactory(private val repo : PersonasDataSource): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PersonasDataSource::class.java).newInstance(repo)
    }
}