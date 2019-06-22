package com.example.animalcare.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.viewModelScope
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.animalcare.database.RoomDB
import com.example.animalcare.database.entities.ley_entity
import com.example.animalcare.database.entities.org_entity
import com.example.animalcare.repositories.LeyRepository
import com.example.animalcare.service.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRoom(context: Application): AndroidViewModel(context){
    private val leyRepository: LeyRepository
    var allLaws: LiveData<List<ley_entity>>
    val listaLeyes: MutableLiveData<MutableList<ley_entity>> = MutableLiveData(emptyList<ley_entity>().toMutableList())
    init {
        val leyDao = RoomDB.getInstance( context,viewModelScope).leyDao()
        //val orgDao= RoomDB.getInstance(context).orgDao()
        //repository= Repository(orgDao, leyDao)
        leyRepository = LeyRepository(leyDao)
        allLaws= leyRepository.allLeyes

    }

    fun getLeyByApartado(apartado: String): LiveData<List<ley_entity>> {
        return leyRepository.getLeyByApartado(apartado)
    }

    fun getLeyByArticulo(articulo: String): LiveData<List<ley_entity>> {
        return leyRepository.getLeyByArticulo(articulo)
    }



    fun getAllLeyes() = viewModelScope.launch(Dispatchers.IO) {
        val response = leyRepository.retrieveLeyes().await()
        if(response.isSuccessful){
            if(response.code()==200){
                listaLeyes.postValue(response.body()?.toMutableList()?:arrayListOf(MoviePreview(Title = "Dummy 1"))
            }
        }
    }




}
